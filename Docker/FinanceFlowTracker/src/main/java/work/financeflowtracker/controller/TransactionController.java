package work.financeflowtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.financeflowtracker.dto.TransactionLimitInfo;
import work.financeflowtracker.entity.Transaction;
import work.financeflowtracker.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Эндпоинт для создания новой транзакции
    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    // Эндпоинт для получения всех транзакций
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // Эндпоинт для получения транзакций, превышающих указанный лимит
    @GetMapping("/exceeding-limit")
    public ResponseEntity<List<Transaction>> getTransactionsExceedingLimit(@RequestParam Double limit) {
        return ResponseEntity.ok(transactionService.getTransactionsExceedingLimit(limit));
    }

    // Эндпоинт для получения транзакций по категории и месяцу
    @GetMapping("/by-category-and-month")
    public ResponseEntity<List<Transaction>> getTransactionsByCategoryAndMonth(
            @RequestParam String category,
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(transactionService.getTransactionsByCategoryAndMonth(category, month, year));
    }

    // Эндпоинт для получения отчета о транзакциях с превышенным лимитом
    @GetMapping("/limit-exceeded-report")
    public ResponseEntity<List<TransactionLimitInfo>> getLimitExceededReport() {
        List<TransactionLimitInfo> report = transactionService.getTransactionsWithLimitExceededInfo();
        return ResponseEntity.ok(report);
    }
}
