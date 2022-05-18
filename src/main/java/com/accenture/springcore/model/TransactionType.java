package com.accenture.springcore.model;

public enum TransactionType {

    SELL("sell"),
    BUY("buy");

    private final String label;
    private TransactionType(String label) {
        this.label = label;
    }
}
