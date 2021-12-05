package com.example.functionalBookstore.domain.cart.core.ports.outgoing;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;

import java.util.Optional;

public interface CustomerInfoDatabase {
    CustomerInfo save(CustomerInfo customerInfo);

    Optional<CustomerInfo> findCustomerInfoByUser(Long userId);
}
