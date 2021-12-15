package com.example.functionalBookstore.domain.order.infrastructure;

import com.example.functionalBookstore.domain.order.core.model.Order;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderDatabase;

import java.util.List;
import java.util.Optional;

public class OrderDatabaseAdapter implements OrderDatabase {

    private final OrderRepository orderRepository;

    @Override
    public Optional<List<Order>> findAllOrders() {
        orderRepository.findAll();
    }
}
