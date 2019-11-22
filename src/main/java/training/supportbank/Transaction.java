package training.supportbank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String fromAccount;
    private String toAccount;
    private String narrative;
    private BigDecimal amount;

    Transaction(LocalDate date, String fromAccount, String toAccount, String narrative, BigDecimal amount){
        this.date = date;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.narrative = narrative;
        this.amount = amount;
    }
}
