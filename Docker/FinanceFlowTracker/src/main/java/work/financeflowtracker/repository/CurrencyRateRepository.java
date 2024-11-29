package work.financeflowtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import work.financeflowtracker.entity.CurrencyRate;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    Optional<CurrencyRate> findByCurrencyAndDate(String currency, LocalDate date);

    CurrencyRate findTopByCurrencyAndDateBeforeOrderByDateDesc(String currency, LocalDate date);
}
