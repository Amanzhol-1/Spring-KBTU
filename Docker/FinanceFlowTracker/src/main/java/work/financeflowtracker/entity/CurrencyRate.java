package work.financeflowtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "currency_rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency", nullable = false, length = 50)
    private String currency;

    @Column(name = "rate", nullable = false, precision = 10, scale = 6)
    private BigDecimal rate;

    @Column(name = "rate_date", nullable = false)
    private LocalDate date;
}

