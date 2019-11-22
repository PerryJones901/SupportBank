package training.supportbank;

import org.apache.commons.io.FilenameUtils;
import java.util.ArrayList;

public interface Parser {

    public ArrayList<Transaction> listOfTransactions(String fileName);

    public static Parser getParser(String fileName){
        switch(FilenameUtils.getExtension(fileName)){
            case "csv" :
            case "txt" :
                return new CSVParser();
            case "json":
                return new JSONParser();
            default    :
                return null;
        }
    }
}