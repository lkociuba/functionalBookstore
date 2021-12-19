package com.example.functionalBookstore.domain.order.core.model;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import com.example.functionalBookstore.domain.user.core.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int orderNumber;
    private BigDecimal calculatedPrice;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;

    @CreationTimestamp
    private LocalDateTime createdTime;

    public Order(){}

    public Order(User user, int orderNumber, BigDecimal calculatedPrice, CustomerInfo customerInfo) {
        this.user = user;
        this.orderNumber = orderNumber;
        this.calculatedPrice = calculatedPrice;
        this.customerName = customerInfo.getCustomerName();
        this.customerAddress = customerInfo.getCustomerAddress();
        this.customerEmail = customerInfo.getCustomerEmail();
        this.customerPhone = customerInfo.getCustomerPhone();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(BigDecimal calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
