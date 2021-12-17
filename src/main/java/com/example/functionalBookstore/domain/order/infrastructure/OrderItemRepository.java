package com.example.functionalBookstore.domain.order.infrastructure;

import com.example.functionalBookstore.domain.order.core.model.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
