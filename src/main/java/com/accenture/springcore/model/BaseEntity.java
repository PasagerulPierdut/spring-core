package com.accenture.springcore.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public class BaseEntity {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;

    public Integer getId() {
        return id;
    }
}
