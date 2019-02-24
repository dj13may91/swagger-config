package com.stock.feecalculator.ReadFactory;

import com.stock.feecalculator.Modals.Report;
import com.stock.feecalculator.Modals.Transaction;
import com.stock.feecalculator.Constants.TransactionFee;
import com.stock.feecalculator.Constants.TransactionType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Scope(scopeName = "singleton")
public class ReadCsvFile implements ReadInterface {

    public String TRANSACTION_FILE_PATH;
    public List<Transaction> transactions = new ArrayList<>();
    public Map<String, List<Report>> reportMap = new HashMap<>();

    public List<Transaction> readTransactionData() {
        FileInputStream csvFile = null;
        try {
            csvFile = new FileInputStream(new File(TRANSACTION_FILE_PATH));
            Scanner scan = new Scanner(csvFile);
            scan.nextLine();
            List<String> transactionLogs = new ArrayList<>();

            while (scan.hasNextLine()) {
                transactionLogs.add(scan.nextLine());
            }
            transactionLogs.stream().forEach((transaction) -> {
                try {
                    transactions.add(createReadableTransaction(transaction));
                } catch (ParseException e) {
                    System.out.println("ERROR in transaction: " + transaction);
                    System.out.println(e);
                }
            });
            transactions.forEach(System.out::println);
            return transactions;
        } catch (FileNotFoundException e) {
            System.out.println("Error reading: " + TRANSACTION_FILE_PATH);
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public Transaction createReadableTransaction(String line) throws ParseException {
        Transaction transaction = new Transaction();
        String stockArr[] = line.split(",");
        Date date = new SimpleDateFormat("dd/mm/yy").parse(stockArr[4].split(" ")[0]);
        transaction.setExternalTransactionId(stockArr[0]);
        transaction.setClientId(stockArr[1]);
        transaction.setSecurityId(stockArr[2]);
        transaction.setTransactionType(stockArr[3]);
        transaction.setTransactionDate(date);
        transaction.setMarketValue(Double.parseDouble(stockArr[5]));
        transaction.setPriorityFlag(stockArr[6]);
        return transaction;
    }

    public List<Transaction> getTransactions() {
        checkTransaction();
        return transactions;
    }

    public Map<String, List<Report>> getReportList() {
        checkTransaction();
        transactions.forEach(transaction -> {
            Report report = new Report();
            report.setClientId(transaction.getClientId());
            report.setTransactionDate(transaction.getTransactionDate());
            report.setTransactionType(transaction.getTransactionType());
            report.setPriority(transaction.getPriorityFlag());
            if (transaction.getPriorityFlag().equals(TransactionType.PRIORITY)) {
                report.setProcessingFee(TransactionFee.transactionFeeMap.get(TransactionType.PRIORITY));
            } else {
                report.setProcessingFee(TransactionFee.transactionFeeMap.get(transaction.getTransactionType()));
            }
            if (reportMap.get(report.getClientId()) == null) {
                List<Report> reportList = new ArrayList<>();
                reportList.add(report);
                reportMap.put(report.getClientId(), reportList);
            } else {
                List<Report> reportList = reportMap.get(report.getClientId());
                reportList.add(report);
                reportMap.put(report.getClientId(), reportList);
            }
        });
        return reportMap;
    }

    public void checkTransaction() {
        if (transactions.size() == 0) {
            generateTransactionData();
        }
    }

    public void generateTransactionData() {
        System.out.println("No data, reading from csv file");
        TRANSACTION_FILE_PATH = ReadFormatFactory.TRANSACTION_FILE_PATH;
        readTransactionData();
    }

    public void setTRANSACTION_FILE_PATH(String TRANSACTION_FILE_PATH) {
        this.TRANSACTION_FILE_PATH = TRANSACTION_FILE_PATH;
    }
}
