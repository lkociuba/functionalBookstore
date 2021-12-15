package com.example.functionalBookstore.domain.order.core.ports.incoming;

import com.example.functionalBookstore.domain.order.core.model.Order;

import java.util.List;
import java.util.Optional;

public interface GetOrders {
    Optional<List<Order>> getOrders();
}
