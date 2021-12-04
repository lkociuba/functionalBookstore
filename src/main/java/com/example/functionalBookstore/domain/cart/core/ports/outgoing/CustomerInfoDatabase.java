package com.example.functionalBookstore.domain.cart.core.ports.outgoing;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;

public interface CustomerInfoDatabase {
    void save(CustomerInfo customerInfo);
}
