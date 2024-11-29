package work.financeflowtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import work.financeflowtracker.entity.Limit;

import java.time.LocalDate;
import java.util.List;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    List<Limit> findByLimitCategory(String category);

    // Запрос для получения лимитов, действующих на определенную дату и категорию
    @Query("SELECT l FROM Limit l WHERE :date BETWEEN l.startDate AND l.endDate AND l.limitCategory = :category ORDER BY l.startDate DESC")
    List<Limit> findLimitsForDateAndCategory(@Param("date") LocalDate date, @Param("category") String category);
}
