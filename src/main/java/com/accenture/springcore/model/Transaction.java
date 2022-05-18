package com.accenture.springcore.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRODUCT")
    @NotNull(message = "Null values not allowed.")
    private String product;

    @Enumerated
    @Column(name = "TRANSACTION_TYPE")
    @NotNull
    private TransactionType transactionType;

    @Column(name = "AMOUNT")
    @Positive(message = "Amount values should be above zero.")
    private double amount;

    public Transaction() {
    }

    public Transaction(Integer id, String product, TransactionType transactionType, double amount) {
        this.id = id;
        this.product = product;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Transaction(String product, TransactionType transactionType, double amount) {
        this.product = product;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public TransactionType getType() {
        return transactionType;
    }

    public void setType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
