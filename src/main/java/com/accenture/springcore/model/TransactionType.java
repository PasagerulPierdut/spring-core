package com.accenture.springcore.model;

public enum TransactionType {

    SELL("SELL"),
    BUY("BUY");

    private final String value;
    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}


