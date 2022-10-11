package com.example.crud.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "picture", schema = "mydb")
public class PictureEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Author")
    private String author;
    @Basic
    @Column(name = "Year")
    private Integer year;
    @Basic
    @Column(name = "Storage")
    private String storage;
    @Basic
    @Column(name = "Price")
    private BigDecimal price;
    @Basic
    @Column(name = "Link")
    private String link;

    public PictureEntity(int id, String name, String author, Integer year, String storage, BigDecimal price, String link) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.storage = storage;
        this.price = price;
        this.link = link;
    }

    public PictureEntity(String name, String author, Integer year, String storage, BigDecimal price, String link) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.storage = storage;
        this.price = price;
        this.link = link;
    }

    public PictureEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureEntity that = (PictureEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(author, that.author) && Objects.equals(year, that.year) && Objects.equals(storage, that.storage) && Objects.equals(price, that.price) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, year, storage, price, link);
    }
}
