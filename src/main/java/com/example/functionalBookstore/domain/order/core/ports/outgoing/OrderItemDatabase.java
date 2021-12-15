package com.example.functionalBookstore.domain.order.core.ports.outgoing;

import com.example.functionalBookstore.domain.order.core.model.OrderItem;

public interface OrderItemDatabase {
    void save(OrderItem orderItem);
}
