package com.accenture.springcore.model;

import com.accenture.springcore.utils.validator.ValidTransaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TRANSACTIONS")
@ValidTransaction
public class Transaction {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "PRODUCT")
    private String product;

    @Enumerated
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType type;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "CREATED")
    private LocalDateTime createdAt;

    @Column(name = "CONFIRMED")
    private boolean confirmed;

    public Transaction() {
    }

    public Transaction(Integer id, Integer userId, String product, TransactionType type,
                       double amount, LocalDateTime createdAt, boolean confirmed) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.confirmed = confirmed;
    }

    public Transaction(Integer userId, String product, TransactionType type, double amount, LocalDateTime createdAt, boolean confirmed) {
        this.userId = userId;
        this.product = product;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.confirmed = confirmed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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


