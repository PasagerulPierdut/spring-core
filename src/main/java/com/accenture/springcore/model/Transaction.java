package com.accenture.springcore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table
public class Transaction {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID")
    private Integer id;

@Column(name = "PRODUCT")
    private String product;

@Enumerated
@Column(name = "TYPE")
    private Type type;

@Column(name = "AMOUNT")
    private double amount;

    public Transaction() {
    }

    public Transaction(Integer id, String product, Type type, double amount) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.amount = amount;
    }

    public Transaction( String product,  Type type, double amount) {
        this.product = product;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
