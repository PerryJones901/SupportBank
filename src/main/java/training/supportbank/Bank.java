package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    private static final Logger LOGGER = LogManager.getLogger();

    private ArrayList<Transaction> listOfTransactions;

    Bank(ArrayList<Transaction> listOfTransactions){
        this.listOfTransactions = listOfTransactions;
    }

    public HashMap<String, BigDecimal> hashMapAccounts(){
        var hashMapAccs = new HashMap<String, BigDecimal>();
        for (Transaction transaction : listOfTransactions) {
            //var date        = listOfTransactions.get(i).getDate();
            var fromAcc = transaction.getFromAccount();
            var toAcc = transaction.getToAccount();
            var money = transaction.getAmount();

            //Initialise (if need be):
            hashMapAccs.putIfAbsent(fromAcc, new BigDecimal(0));
            hashMapAccs.putIfAbsent(toAcc, new BigDecimal(0));
            //Do the maths:
            hashMapAccs.put(fromAcc, hashMapAccs.get(fromAcc).subtract(money));
            hashMapAccs.put(toAcc, hashMapAccs.get(toAcc).add(money));
        }
        return hashMapAccs;
    }

    public void listAcc(String name){
        for (Transaction t : listOfTransactions) {
            if(t.getFromAccount().equals(name))
                System.out.println(name + " paid " +
                        t.getToAccount() + " £" + t.getAmount() + " for " + t.getNarrative());
            if(t.getToAccount().equals(name))
                System.out.println(t.getFromAccount() + " paid " +
                        name + " £" + t.getAmount() + " for " + t.getNarrative());
        }
    }

    public void listAll(){
        for (Transaction t : listOfTransactions) {
            System.out.println("On " + t.getDate() +
                    " " + t.getFromAccount() + " paid " + t.getToAccount() +
                    " £" + t.getAmount() + " for " + t.getNarrative() + ".");
        }
    }
}
