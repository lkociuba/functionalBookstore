package com.example.functionalBookstore.domain.order.infrastructure;

import com.example.functionalBookstore.domain.order.core.model.Order;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderDatabaseAdapter implements OrderDatabase {

    private final OrderRepository orderRepository;

    @Override
    public Optional<List<Order>> findAllOrders() {
        try {
            return Optional.ofNullable(orderRepository.findAll());
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
