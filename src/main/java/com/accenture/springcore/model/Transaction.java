package com.accenture.springcore.model;

import com.accenture.springcore.utils.validator.ValidTransaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
@ValidTransaction
public class Transaction extends BaseEntity {

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "PRODUCT")
    private String product;

    @Enumerated
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "CREATED")
    private LocalDateTime createdAt;

    @Column(name = "CONFIRMED")
    private boolean confirmed;

    public Transaction() {
    }

    public Transaction(Integer userId, String product, TransactionType transactionType, double amount,
                       LocalDateTime createdAt, boolean confirmed) {
        this.userId = userId;
        this.product = product;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = createdAt;
        this.confirmed = confirmed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "product='" + product + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }
}


