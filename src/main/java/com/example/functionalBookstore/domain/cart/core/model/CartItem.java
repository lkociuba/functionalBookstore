package com.example.functionalBookstore.domain.cart.core.model;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.user.core.model.User;

import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public CartItem() {
    }

    public CartItem(Book book, int quantity, User user) {
        this.book = book;
        this.quantity = quantity;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
