package com.example.functionalBookstore.domain.order.infrastructure;

import com.example.functionalBookstore.domain.order.core.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
