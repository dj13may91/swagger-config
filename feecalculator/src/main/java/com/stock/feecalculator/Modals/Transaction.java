package com.stock.feecalculator.Modals;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    private String externalTransactionId;
    private String clientId;
    private String securityId;
    private String transactionType;
    private Date transactionDate;
    private double marketValue;
    private String priorityFlag;
    private boolean isIntraDay;

}
