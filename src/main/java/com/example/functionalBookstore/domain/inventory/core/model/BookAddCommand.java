package com.example.functionalBookstore.domain.inventory.core.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class BookAddCommand {

    private String name;
    private String description;
    private BigDecimal price;

    public BookAddCommand() {
    }

    public BookAddCommand(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
}
