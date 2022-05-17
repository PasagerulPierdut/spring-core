package com.accenture.springcore.model;

public enum Type {

    SELL("sell"),
    BUY("buy");

    private final String label;
    private Type(String label) {
        this.label = label;
    }
}
