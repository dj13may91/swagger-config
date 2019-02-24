package com.stock.feecalculator.Modals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private String clientId;
    private String transactionType;
    private Date transactionDate;
    private String priority;
    private double processingFee;
}
