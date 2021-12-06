package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CustomerInfoDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerInfoDatabaseAdapter implements CustomerInfoDatabase {

    private final CustomerInfoRepository customerInfoRepository;

    @Override
    public CustomerInfo save(CustomerInfo customerInfo) {
        return customerInfoRepository.save(customerInfo);
    }

    @Override
    public Optional<CustomerInfo> findCustomerInfoByUser(Long userId) {
        try {
            return Optional.ofNullable(customerInfoRepository.findByUserId(userId));
        } catch (DataAccessException exception) {
                    return Optional.empty();
        }
    }

    @Override
    public void delete(CustomerInfo customerInfo) {
        customerInfoRepository.delete(customerInfo);
    }
}
