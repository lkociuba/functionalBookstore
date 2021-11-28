package com.example.functionalBookstore.domain.cart.core.model;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.user.core.model.User;

public class AddCartItemCommand {

    private Book book;
    private final int quantity = 1;
    private User user;

    public AddCartItemCommand(){}

    public AddCartItemCommand(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}