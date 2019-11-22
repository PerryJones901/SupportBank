package training.supportbank;

import java.math.BigDecimal;

public class Account {

    //Variables
    private String name;
    private BigDecimal balance;

    public void add(BigDecimal money){
        this.balance = this.balance.add(money);
    }
    public void sub(BigDecimal money){
        this.balance = this.balance.subtract(money);
    }
}
