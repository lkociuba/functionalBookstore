package com.example.functionalBookstore.domain.order.core.model;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import com.example.functionalBookstore.domain.user.core.model.User;

import java.math.BigDecimal;

public class AddOrderCommand {

    private User user;
    private int orderNumber;
    private BigDecimal calculatedPrice;
    private CustomerInfo customerInfo;

    public AddOrderCommand setUser(User user) {
        this.user = user;
        return this;
    }

    public AddOrderCommand setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public AddOrderCommand setCalculatedPrice(BigDecimal calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
        return this;
    }

    public AddOrderCommand setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
        return this;
    }

    public Order getOrder() {
        return new Order(user, orderNumber, calculatedPrice, customerInfo);
    }
}
