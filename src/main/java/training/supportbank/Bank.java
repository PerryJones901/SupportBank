package training.supportbank;

import java.util.ArrayList;

public class Bank {

    //Variables
    private String name;
    private ArrayList<Transaction> listOfTransactions;
    private ArrayList<String> bankUsers;

    //Constructor
    Bank(String name){
        this.name = name;
    }

    //Setters
    public void setListOfTransactions(ArrayList<Transaction> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }
}
