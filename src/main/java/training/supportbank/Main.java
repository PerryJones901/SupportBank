package training.supportbank;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

import com.google.gson.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Main {

    //--- Logger ---
    private static final Logger LOGGER = LogManager.getLogger();

    //--- Main ---
    public static void main(String args[]) throws IOException {

        //Take user input for file name:
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //Read the file:
        ArrayList<Transaction> listOfTransactions = Parser.getParser(input).listOfTransactions(input);

        //Create a bank:
        var bank = new Bank(listOfTransactions);

        //Print all transactions:
        bank.listAll();

        //Print transactions by name:
        var name = "Todd";
        bank.listAcc(name);
    }























    //--- List[Account] ---
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

    //--- List All ---
    private static void listAll(ArrayList<String[]> listOfTransactions){
        //Make a HashMap of Accounts

        var hashMapAccs = new HashMap<String, BigDecimal>();
        for(int i = 1; i < listOfTransactions.size(); i++){
            var date        = listOfTransactions.get(i)[0];
            var fromName    = listOfTransactions.get(i)[1];
            var toName      = listOfTransactions.get(i)[2];


            //Check money is in correct format:
            BigDecimal money = new BigDecimal(0);
            try{
                money = new BigDecimal(listOfTransactions.get(i)[4]);
            } catch (NumberFormatException nfe) {
                //nfe.printStackTrace();
                LOGGER.error("Failure to parse '" + listOfTransactions.get(i)[4] +
                        "' to BigDecimal at line: " + i);
            }

            //Check date is in correct format:
            try{
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate d = LocalDate.parse(date, format);
            } catch (DateTimeParseException dtpe) {
                //dtpe.printStackTrace();
                LOGGER.error("Failure to parse '" + date +"' at line : " + i);

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