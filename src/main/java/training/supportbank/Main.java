package training.supportbank;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String args[]) throws IOException {
        //Part 1: Reading CSV Files:
        //  List of Transactions
        var listOfTransactions = new ArrayList<String[]>();

        //  Reading File and Adding to list
        //FileInputStream fstream = new FileInputStream("Transactions2014.txt");
        FileInputStream fstream = new FileInputStream("DodgyTransactions2015.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        int count = 0;
        while ((strLine = br.readLine()) != null)   {
            listOfTransactions.add(strLine.split(","));
            //System.out.println(listOfTransactions.get(count)[0]);
            count++;
        }

        //listAcc("Todd",listOfTransactions);
        listAll(listOfTransactions);
    }

    //List[Account]:
    private static void listAcc(String accountName, ArrayList<String[]> listOfTransactions){
        for(int i = 1; i < listOfTransactions.size(); i++) {

            if (listOfTransactions.get(i)[1].equals(accountName)) {
                //Paying Others.
                System.out.println(accountName +
                        " paid " + listOfTransactions.get(i)[2] + " £" +
                        listOfTransactions.get(i)[4] + " for " + listOfTransactions.get(i)[3] +
                        " on " + listOfTransactions.get(i)[0]);
            } else if(listOfTransactions.get(i)[2].equals(accountName)){
                //Getting Paid.
                System.out.println(accountName +
                        " was paid by " + listOfTransactions.get(i)[1] + " £" +
                        listOfTransactions.get(i)[4] + " for " + listOfTransactions.get(i)[3] +
                        " on " + listOfTransactions.get(i)[0]);
            }
        }
    }
    //List All:
    private static void listAll(ArrayList<String[]> listOfTransactions){
        //Make a HashMap of Accounts

        var hashMapAccs = new HashMap<String, BigDecimal>();
        for(int i = 1; i < listOfTransactions.size(); i++){
            var fromName = listOfTransactions.get(i)[1];
            var toName = listOfTransactions.get(i)[2];
            BigDecimal money = new BigDecimal(0);
            try{
                money = new BigDecimal(listOfTransactions.get(i)[4]);
            } catch (NumberFormatException nfe) {
                //Normal Output: nfe.printStackTrace();
                LOGGER.error("Failure to parse money to BigDecimal at line: " + i);
            }

            //Initialise (if need be):
            hashMapAccs.putIfAbsent(fromName, new BigDecimal(0));
            hashMapAccs.putIfAbsent(toName, new BigDecimal(0));
            //Do the maths:
            hashMapAccs.put(fromName, hashMapAccs.get(fromName).subtract(money));
            hashMapAccs.put(toName, hashMapAccs.get(toName).add(money));
        }
        //Now iterate through the HashMap.
        // Get a set of the entries
        Set set = hashMapAccs.entrySet();
        // Get an iterator
        Iterator i = set.iterator();

        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + ": " + me.getValue() + "\n");
        }
    }
}