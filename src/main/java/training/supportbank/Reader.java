package training.supportbank;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Reader {
    private static final Logger LOGGER = LogManager.getLogger();

    private ArrayList<String[]> listOfTransactions;

    Reader(String path){
        switch(FilenameUtils.getExtension(path)){
            case "csv" :
                readFromCSV(path);
                break;
            case "txt" :
                readFromTxt(path);
                break;
            case "json":
                readFromJSON(path);
                break;
            default    :
                LOGGER.error("File extension not supported.");
                break;
        }
    }

    //Methods
    private static void readFromCSV(String path){
        FileInputStream fstream;
        BufferedReader br = new BufferedReader(Reader.nullReader());
        try{
            fstream = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(fstream));
        } catch(FileNotFoundException e){
            LOGGER.error("Error reading CSV File.");
        }
        String strLine;
        try{
            while ((strLine = br.readLine()) != null)   {
                listOfTransactions.add(strLine.split(","));
            }
        } catch (IOException e){
            LOGGER.error("Error reading lines from CSV File.");
        }
        return listOfTransactions;
    }
    private static void readFromTxt(String path){
        
    }
    private static void readFromJSON(String path){

    }
}
