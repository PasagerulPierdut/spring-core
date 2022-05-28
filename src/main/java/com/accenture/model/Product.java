package com.accenture.model;

import com.accenture.model.Transaction;
import com.accenture.springcore.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "NAME")
    @NotBlank(message = "Product name is missing.")
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(min = 5, message = "Minimum 5 characters are required..")
    private String description;

    @Column(name = "CREATED")
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED")
    private LocalDateTime modifiedAt;

    @OneToMany(targetEntity = Transaction.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Transaction> transactions;

    public Product() {
    }

    public Product(String name, String description, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "Product " +
                ", name: " + name + '\'' +
                ", description:'" + description + '\'' +
                ", createdAt: " + createdAt +
                ", modifiedAt: " + modifiedAt +
                '}';
    }
}
