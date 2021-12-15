package com.example.functionalBookstore.domain.order.core.ports.outgoing;

import com.example.functionalBookstore.domain.order.core.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDatabase {
    Optional<List<Order>> findAllOrders();
}
