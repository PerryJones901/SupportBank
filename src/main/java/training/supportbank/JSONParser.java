package training.supportbank;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JSONParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger();

    public ArrayList<Transaction> listOfTransactions(String fileName){
        //Reading JSON files
        JsonParser parser = new JsonParser();
        JsonArray a = new JsonArray();
        try{
            a = (JsonArray) parser.parse(new FileReader("Transactions2013.json"));
        } catch (FileNotFoundException e){
            LOGGER.error("Error reading JSON file.");
        }
        System.out.println(a.get(0));

        //GsonBuilder Setup
        GsonBuilder gsonBuilder = new GsonBuilder();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                LocalDate.parse(jsonElement.getAsString(),format)
        );
        Gson gson = gsonBuilder.create();

        //Plugging into a ArrayList of Transactions:
        var listOfTransactions = new ArrayList<Transaction>();
        a.forEach(elem -> listOfTransactions.add(gson.fromJson(elem), Transaction.class));

        return null;
    }
}
