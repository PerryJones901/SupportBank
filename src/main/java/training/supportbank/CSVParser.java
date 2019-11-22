package training.supportbank;

import org.apache.commons.io.FilenameUtils;

import java.util.ArrayList;

public class CSVParser implements Parser {
    public ArrayList<Transaction> listOfTransactions(String fileName){
        FilenameUtils.getExtension(fileName)
    }
}
