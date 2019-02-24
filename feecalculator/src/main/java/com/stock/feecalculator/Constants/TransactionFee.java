package com.stock.feecalculator.Constants;

import java.util.HashMap;
import java.util.Map;

public class TransactionFee {

    public static final Map<String, Integer> transactionFeeMap = new HashMap<String, Integer>(){
        {
            put(TransactionType.BUY, 50);
            put(TransactionType.DEPOSIT, 50);
            put(TransactionType.WITHDRAW, 100);
            put(TransactionType.SELL, 100);
            put(TransactionType.PRIORITY, 500);
        }
    };
}
