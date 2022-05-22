package com.accenture.springcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction extends BaseEntity {

    @Column(name = "USER_ID")
    private Integer userId;

    @Enumerated
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "CREATED")
    private LocalDateTime createdAt;

    @Column(name = "CONFIRMED")
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Transaction() {
    }

    public Transaction(Integer userId, TransactionType transactionType, double amount,
                       LocalDateTime createdAt, boolean confirmed, Product product) {
        this.userId = userId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = createdAt;
        this.confirmed = confirmed;
        this.product = product;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product Product) {
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
                "userId=" + userId +
                ", product=" + product +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", confirmed=" + confirmed +
                '}';
    }
}



