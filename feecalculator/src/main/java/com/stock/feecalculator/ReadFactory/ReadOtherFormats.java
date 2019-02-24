package com.stock.feecalculator.ReadFactory;

import com.stock.feecalculator.Modals.Report;
import com.stock.feecalculator.Modals.Transaction;

import java.util.List;
import java.util.Map;

public class ReadOtherFormats implements ReadInterface {
    // TODO: will be implemented in future

    @Override
    public List<Transaction> readTransactionData() {
        return null;
    }

    @Override
    public Transaction createReadableTransaction(String line) {
        return null;
    }

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public Map<String, List<Report>> getReportList() {
        return null;
    }

    @Override
    public void checkTransaction() {
    }

    @Override
    public void generateTransactionData() {
    }

    @Override
    public void setTRANSACTION_FILE_PATH(String TRANSACTION_FILE_PATH) {
    }
}
