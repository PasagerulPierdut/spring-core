package com.accenture.springcore.model;

public class Criteria {

    private String product;
    private TransactionType type;
    private Double minAmount;
    private Double maxAmount;

    public Criteria() {
    }

    public Criteria(String product, TransactionType type, Double minAmount, Double maxAmount) {
        this.product = product;
        this.type = type;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public String getProduct() {
        return product;
    }

    public Enum getType() {
        return type;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

}
