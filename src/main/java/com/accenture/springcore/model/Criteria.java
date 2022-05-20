package com.accenture.springcore.model;

import java.time.LocalDateTime;

//TODO replace with Builder , maybe.
public class Criteria {

    private String product;
    private TransactionType type;
    private Double minAmount;
    private Double maxAmount;
    private LocalDateTime createdAt;
    private boolean confirmed;

    public Criteria() {
    }

    public Criteria(String product, TransactionType type, Double minAmount, Double maxAmount, LocalDateTime createdAt, boolean confirmed) {
        this.product = product;
        this.type = type;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.createdAt = createdAt;
        this.confirmed = confirmed;
    }

    public String getProduct() {
        return product;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
