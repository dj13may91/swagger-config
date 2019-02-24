package com.stock.feecalculator.ReadFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadFormatFactory {

    @Autowired
    ReadInterface csvFile;

    private static final String supportMsg = "Support will be added in future implementations";
    public static final String TRANSACTION_FILE_PATH = "/Users/divya.jain/Desktop/transactions.csv";

    public ReadInterface readCsv() {
        csvFile.setTRANSACTION_FILE_PATH(TRANSACTION_FILE_PATH);
        return csvFile;
    }

    public String readPdf() {
        return supportMsg;
    }

    public String readExcel() {
        return supportMsg;
    }

    public String readXml() {
        return supportMsg;

    }

    public String readPipeDelimitedTextFile() {
        return supportMsg;
    }
}
