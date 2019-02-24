package com.stock.feecalculator.ReadFactory;

import com.stock.feecalculator.Modals.Report;
import com.stock.feecalculator.Modals.Transaction;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface ReadInterface {

    List<Transaction> readTransactionData();

    Transaction createReadableTransaction(String line) throws ParseException;

    List<Transaction> getTransactions();

    Map<String, List<Report>> getReportList();

    void checkTransaction();

    void generateTransactionData();

    void setTRANSACTION_FILE_PATH(String TRANSACTION_FILE_PATH);
}
