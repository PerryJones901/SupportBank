package training.supportbank;

import com.google.gson.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public interface Parser {

    public ArrayList<Transaction> listOfTransactions(String fileName);
    public static Parser getParser(String fileName){
        switch(FilenameUtils.getExtension(path)){
            case "csv" :
                return new CSVParser();
            case "txt" :
                return new TXTParser();
            case "json":
                return new JSONParser();
            default    :

        }
    }
//    Parser(String path){
//        switch(FilenameUtils.getExtension(path)){
//            case "csv" :
//                readFromCSV(path);
//                break;
//            case "txt" :
//                readFromTxt(path);
//                break;
//            case "json":
//                readFromJSON(path);
//                break;
//            default    :
//                LOGGER.error("File extension not supported.");
//                break;
//        }
//    }
//
//    public ArrayList<Transaction> getListOfTransactions() {
//        return listOfTransactions;
//    }
//
//    //Methods
    private static void readFromCSV(String path){
        FileInputStream fstream;
        BufferedReader br = new BufferedReader(Parser.nullReader());
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
//    private static void readFromTxt(String path){
//        FileInputStream fstream;
//        BufferedReader br = new BufferedReader(Parser.nullReader());
//        try{
//            fstream = new FileInputStream(path);
//            br = new BufferedReader(new InputStreamReader(fstream));
//        } catch(FileNotFoundException e){
//            LOGGER.error("Error reading CSV File.");
//        }
//        String strLine;
//        try{
//            while ((strLine = br.readLine()) != null)   {
//                listOfTransactions.add(strLine.split(","));
//            }
//        } catch (IOException e){
//            LOGGER.error("Error reading lines from CSV File.");
//        }
//        return listOfTransactions;
//    }
//    private static void readFromJSON(String path){
//        //Reading JSON files
//        JsonParser parser = new JsonParser();
//        JsonArray a = new JsonArray();
//        try{
//            a = (JsonArray) parser.parse(new FileReader("Transactions2013.json"));
//        } catch (FileNotFoundException e){
//            LOGGER.error("Error reading JSON file.");
//        }
//        System.out.println(a.get(0));
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
//                LocalDate.parse(jsonElement.getAsString(),format)
//        );
//        Gson gson = gsonBuilder.create();
//        return null;
//    }
}