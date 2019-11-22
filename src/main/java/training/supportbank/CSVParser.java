package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CSVParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger();

    public ArrayList<Transaction> listOfTransactions(String fileName){
        //So our file is a CSV File.
        FileInputStream fstream;
        BufferedReader br = new BufferedReader(Reader.nullReader());
        var data = new ArrayList<String[]>();
        try{
            fstream = new FileInputStream(fileName);
            br = new BufferedReader(new InputStreamReader(fstream));
        } catch(FileNotFoundException e){
            LOGGER.error("Error reading CSV File.");
        }
        String strLine;
        try{
            while ((strLine = br.readLine()) != null)   {
                data.add(strLine.split(","));
            }
        } catch (IOException e){
            LOGGER.error("Error reading lines from CSV File.");
        }
        //Now create an ArrayList of Transactions.
        var listOfTransactions = new ArrayList<Transaction>();
        for(int i = 1; i < data.size(); i++){
            //Index starts at 1 to ignore headings.
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(data.get(i)[0], format);
            BigDecimal money = new BigDecimal(data.get(i)[4]);
            listOfTransactions.add(new Transaction(d,data.get(i)[1],data.get(i)[2],data.get(i)[3],money));
        }
        return listOfTransactions;
    }
}
