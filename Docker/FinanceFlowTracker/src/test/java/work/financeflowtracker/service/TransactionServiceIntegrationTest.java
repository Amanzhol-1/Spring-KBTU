package work.financeflowtracker.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import work.financeflowtracker.entity.Limit;
import work.financeflowtracker.entity.Transaction;
import work.financeflowtracker.repository.CurrencyRateRepository;
import work.financeflowtracker.repository.LimitRepository;
import work.financeflowtracker.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceIntegrationTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Autowired
    private LimitRepository limitRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @AfterEach
    public void tearDown() {
        transactionRepository.deleteAll();
        limitRepository.deleteAll();
        currencyRateRepository.deleteAll();
    }

    @Test
    public void testSaveTransaction_LimitExceeded_WithRealCurrencyRates() {
        // Обновляем курсы валют
        currencyService.updateDailyRates();

        // Проверяем, что курсы валют сохранены
        Assertions.assertFalse(currencyRateRepository.findAll().isEmpty(), "Currency rates should be saved in the database.");

        // Устанавливаем лимит
        Limit limit = new Limit();
        limit.setLimitSum(new BigDecimal("1000.00"));
        limit.setLimitCategory("Товары");
        limit.setStartDate(LocalDate.now());
        limit.setEndDate(LocalDate.now().plusDays(30));
        limit.setLimitCurrencyShortname("USD");
        limit.setLimitExceeded(false);
        limitRepository.save(limit);

        // Создаем первую транзакцию в долларах (USD)
        Transaction transaction1 = new Transaction();
        transaction1.setAccountFrom(1L);
        transaction1.setAccountTo(2L);
        transaction1.setCurrencyShortname("USD");
        transaction1.setSum(new BigDecimal("500.00")); // 500 USD
        transaction1.setExpenseCategory("Товары");
        transaction1.setDatetime(LocalDateTime.now());
        transactionService.saveTransaction(transaction1);

        // Обновляем состояние транзакции после сохранения
        transaction1 = transactionRepository.findById(transaction1.getId()).orElseThrow();

        // Проверяем, что первая транзакция не превысила лимит
        Assertions.assertFalse(transaction1.getLimitExceeded(), "First transaction should not have exceeded the limit.");

        // Создаем вторую транзакцию в тенге (KZT)
        Transaction transaction2 = new Transaction();
        transaction2.setAccountFrom(1L);
        transaction2.setAccountTo(2L);
        transaction2.setCurrencyShortname("KZT");
        transaction2.setSum(new BigDecimal("300000.00"));
        transaction2.setExpenseCategory("Товары");
        transaction2.setDatetime(LocalDateTime.now());

        // Сохраняем транзакцию
        Transaction savedTransaction = transactionService.saveTransaction(transaction2);

        // Обновляем состояние транзакции после сохранения
        savedTransaction = transactionRepository.findById(savedTransaction.getId()).orElseThrow();

        // Проверяем результаты
        Assertions.assertNotNull(savedTransaction.getId());
        Assertions.assertTrue(savedTransaction.getLimitExceeded(), "Second transaction should have exceeded the limit.");
    }
}