package work.financeflowtracker.dto;

import lombok.Getter;
import lombok.Setter;
import work.financeflowtracker.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionLimitInfo {
    private Transaction transaction;
    private BigDecimal limitSum;
    private String limitCurrency;
    private LocalDate limitStartDate;

    public TransactionLimitInfo(Transaction transaction, BigDecimal limitSum, String limitCurrency, LocalDate limitStartDate) {
        this.transaction = transaction;
        this.limitSum = limitSum;
        this.limitCurrency = limitCurrency;
        this.limitStartDate = limitStartDate;
    }
}
