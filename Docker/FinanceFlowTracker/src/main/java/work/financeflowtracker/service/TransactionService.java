package work.financeflowtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.financeflowtracker.entity.Limit;
import work.financeflowtracker.entity.Transaction;
import work.financeflowtracker.repository.LimitRepository;
import work.financeflowtracker.repository.TransactionRepository;
import work.financeflowtracker.dto.TransactionLimitInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LimitRepository limitRepository;
    private final CurrencyService currencyService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, LimitRepository limitRepository, CurrencyService currencyService) {
        this.transactionRepository = transactionRepository;
        this.limitRepository = limitRepository;
        this.currencyService = currencyService;
    }

    public Transaction saveTransaction(Transaction transaction) {

        LocalDate transactionDate = transaction.getDatetime().toLocalDate();

        Limit applicableLimit = getApplicableLimit(transactionDate, transaction.getExpenseCategory());

        if (applicableLimit == null) {
            applicableLimit = getDefaultLimit(transactionDate, transaction.getExpenseCategory());
        }

        BigDecimal transactionAmountUSD = convertTransactionToUSD(transaction);

        BigDecimal totalSumUSD = getTotalTransactionsSumForLimitPeriod(applicableLimit, transaction);

        totalSumUSD = totalSumUSD.add(transactionAmountUSD);

        if (totalSumUSD.compareTo(applicableLimit.getLimitSum()) > 0) {
            transaction.setLimitExceeded(true);
        } else {
            transaction.setLimitExceeded(false);
        }

        return transactionRepository.save(transaction);
    }

    private BigDecimal convertTransactionToUSD(Transaction transaction) {
        String currency = transaction.getCurrencyShortname();
        BigDecimal amount = transaction.getSum();

        if ("USD".equalsIgnoreCase(currency)) {
            return amount;
        } else {
            BigDecimal exchangeRate = currencyService.getExchangeRate(currency, transaction.getDatetime().toLocalDate());
            return amount.divide(exchangeRate, 2, RoundingMode.HALF_UP);
        }
    }

    private BigDecimal getTotalTransactionsSumForLimitPeriod(Limit limit, Transaction transaction) {
        LocalDateTime startDateTime = limit.getStartDate().atStartOfDay();
        LocalDateTime endDateTime = transaction.getDatetime();

        List<Transaction> transactions = transactionRepository.findTransactionsInPeriodAndCategory(
                startDateTime, endDateTime, limit.getLimitCategory());

        BigDecimal totalSumUSD = transactions.stream()
                .map(t -> convertTransactionToUSD(t))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSumUSD;
    }

    private Limit getApplicableLimit(LocalDate transactionDate, String category) {
        List<Limit> limits = limitRepository.findLimitsForDateAndCategory(transactionDate, category);
        if (!limits.isEmpty()) {
            return limits.get(0);
        }
        return null;
    }

    private Limit getDefaultLimit(LocalDate transactionDate, String category) {
        LocalDate startDate = transactionDate.withDayOfMonth(1);
        LocalDate endDate = transactionDate.withDayOfMonth(transactionDate.lengthOfMonth());

        Limit defaultLimit = new Limit();
        defaultLimit.setLimitSum(new BigDecimal("1000.00"));
        defaultLimit.setLimitCategory(category);
        defaultLimit.setStartDate(startDate);
        defaultLimit.setEndDate(endDate);
        defaultLimit.setLimitCurrencyShortname("USD");
        defaultLimit.setLimitExceeded(false);

        return defaultLimit;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsExceedingLimit(Double limit) {
        return transactionRepository.findTransactionsExceedingLimit(limit);
    }

    public List<Transaction> getTransactionsByCategoryAndMonth(String category, int month, int year) {
        return transactionRepository.findByCategoryAndMonth(category, month, year);
    }

    public List<TransactionLimitInfo> getTransactionsWithLimitExceededInfo() {
        List<Transaction> transactions = transactionRepository.findByLimitExceededTrue();

        return transactions.stream().map(transaction -> {
            Limit limit = getApplicableLimit(transaction.getDatetime().toLocalDate(), transaction.getExpenseCategory());
            BigDecimal limitSum = limit != null ? limit.getLimitSum() : new BigDecimal("1000.00");
            String limitCurrency = limit != null ? limit.getLimitCurrencyShortname() : "USD";
            LocalDate limitDate = limit != null ? limit.getStartDate() : transaction.getDatetime().toLocalDate().withDayOfMonth(1);

            return new TransactionLimitInfo(transaction, limitSum, limitCurrency, limitDate);
        }).collect(Collectors.toList());
    }
}
