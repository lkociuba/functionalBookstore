package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CustomerInfoDatabase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerInfoDatabaseAdapter implements CustomerInfoDatabase {

    private final CustomerInfoRepository customerInfoRepository;

    @Override
    public void save(CustomerInfo customerInfo) {
        customerInfoRepository.save(customerInfo);
    }
}
