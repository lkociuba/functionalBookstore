package com.example.functionalBookstore.domain.order.core.model;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.inventory.core.model.Book;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartItem_id")
    private CartItem cartItem;

    private BigDecimal price;
    private int quantity;

    public OrderItem(){}

    public OrderItem(Order order, CartItem cartItem) {
        this.order = order;
        this.book = cartItem.getBook();
        this.price = cartItem.getBook().getPrice();
        this.quantity = cartItem.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
