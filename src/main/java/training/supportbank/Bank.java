package training.supportbank;

import java.util.ArrayList;

public class Bank {

    //Variables
    private String name;
    private ArrayList<String[]> listOfTransactions;

    //Constructor
    Bank(String name){
        this.name = name;
    }

    //Setters
    public void setListOfTransactions(ArrayList<String[]> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }
}
