package work.financeflowtracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "limits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_sum", nullable = false, precision = 10, scale = 2)
    private BigDecimal limitSum;

    @Column(name = "limit_category", nullable = false, length = 50)
    private String limitCategory;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "limit_currency_shortname", nullable = false, length = 10)
    private String limitCurrencyShortname;

    @Column(name = "limit_exceeded", nullable = false)
    private Boolean limitExceeded = false;
}
