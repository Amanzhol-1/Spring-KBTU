package work.financeflowtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import work.financeflowtracker.entity.Transaction;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Метод для поиска транзакций, сумма которых превышает указанный лимит
    @Query("SELECT t FROM Transaction t WHERE t.sum > :limit")
    List<Transaction> findTransactionsExceedingLimit(@Param("limit") Double limit);

    // Запрос для поиска транзакций по категории и месяцу
    @Query("SELECT t FROM Transaction t WHERE t.expenseCategory = :category AND FUNCTION('MONTH', t.datetime) = :month AND FUNCTION('YEAR', t.datetime) = :year")
    List<Transaction> findByCategoryAndMonth(@Param("category") String category, @Param("month") int month, @Param("year") int year);

    // Запрос для получения транзакций в определенный период и категории
    @Query("SELECT t FROM Transaction t WHERE t.datetime >= :startDateTime AND t.datetime <= :endDateTime AND t.expenseCategory = :category")
    List<Transaction> findTransactionsInPeriodAndCategory(@Param("startDateTime") LocalDateTime startDateTime,
                                                          @Param("endDateTime") LocalDateTime endDateTime,
                                                          @Param("category") String category);

    List<Transaction> findByLimitExceededTrue();
}
