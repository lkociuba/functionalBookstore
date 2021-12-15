package com.example.functionalBookstore.domain.order.infrastructure;

import com.example.functionalBookstore.domain.order.core.model.OrderItem;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderItemDatabase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderItemDatabaseAdapter implements OrderItemDatabase {

    private final OrderItemRepository orderItemRepository;

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
