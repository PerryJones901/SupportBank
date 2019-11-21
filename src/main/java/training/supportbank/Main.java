package training.supportbank;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String args[]) throws IOException {
        //Part 1: Reading CSV Files:
        //  List of Transactions
        var listOfTransactions = new LinkedList<Array>();

        //  Reading File and Adding to list
        FileInputStream fstream = new FileInputStream("Transactions2014.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null)   {
            //Handle string.
        }

        //  HashMaps
        var accounts = new HashMap<String, Integer>();
        var transactions = new HashMap<String, Integer>();



        System.out.println("Test!");
    }
}