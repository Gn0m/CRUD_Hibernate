package com.example.crud.Database;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Picture implements Serializable {

    private static final long serialVersionUID = -7124516917206618209L;
    private int id;
    private String name;
    private String author;
    private int year;
    private String storage;
    private double price;
    private String link;

    public Picture() {
    }

    public Picture(String name, String author, int year, String storage, double price, String link) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.storage = storage;
        this.price = price;
        this.link = link;
    }

    public Picture(int id, String name, String author, int year, String storage, double price, String link) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.storage = storage;
        this.price = price;
        this.link = link;
    }

}
