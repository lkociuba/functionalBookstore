package com.example.functionalBookstore.domain.inventory.core.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Long bookId;

    private String name;
    private String description;
    private BigDecimal price;

    @CreationTimestamp
    private LocalDateTime createdTime;

    public Book(){}

    public Book(String name, String description, BigDecimal price, LocalDateTime createdTime) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdTime = createdTime;
    }

    public Long getBookId() {
        return bookId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
