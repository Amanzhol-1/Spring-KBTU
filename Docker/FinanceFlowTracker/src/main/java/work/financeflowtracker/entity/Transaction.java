package work.financeflowtracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from", nullable = false)
    private Long accountFrom;

    @Column(name = "account_to", nullable = false)
    private Long accountTo;

    @Column(name = "currency_shortname", nullable = false, length = 3)
    private String currencyShortname;

    @Column(name = "sum", nullable = false, precision = 10, scale = 2)
    private BigDecimal sum;

    @Column(name = "expense_category", nullable = false, length = 50)
    private String expenseCategory;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "limit_exceeded", nullable = false)
    private Boolean limitExceeded = false;
}
