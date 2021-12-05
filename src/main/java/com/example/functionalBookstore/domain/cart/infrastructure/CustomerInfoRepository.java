package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends CrudRepository<CustomerInfo, Long> {
    CustomerInfo findByUserId(Long userId);
}
