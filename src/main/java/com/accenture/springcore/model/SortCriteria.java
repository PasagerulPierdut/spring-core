package com.accenture.springcore.model;

//TODO replace with Builder , maybe.
public class SortCriteria {

    private Integer id;
    private Integer userId;
    private String product;
    private TransactionType transactionType;
    private double minAmount;
    private double maxAmount;
    private String startDateTime;
    private String endDateTime;
    private boolean confirmed;

    public SortCriteria() {
    }

    public SortCriteria(String product) {
        this.product = product;
    }

    public SortCriteria(Integer id, Integer userId, String product, TransactionType transactionType, double minAmount, double maxAmount, String startDateTime, String endDateTime, boolean confirmed) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.transactionType = transactionType;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.confirmed = confirmed;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getProduct() {
        return product;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
