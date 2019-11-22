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

    public void printTransactionsOf(String name){
        for (Transaction transaction : listOfTransactions) {
            
        }
    }
}
