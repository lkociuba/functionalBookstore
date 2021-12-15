package com.example.functionalBookstore.domain.order.core;

import com.example.functionalBookstore.domain.order.core.model.Order;
import com.example.functionalBookstore.domain.order.core.ports.incoming.AddNewOrder;
import com.example.functionalBookstore.domain.order.core.ports.incoming.GetOrders;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderDatabase;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderService implements AddNewOrder, GetOrders {

    private final GetLoggedUser getLoggedUser;
    private final OrderDatabase orderDatabase;

    @Override
    public void saveOrder() {
        var user = getLoggedUser.getLoggedUser();

    }

    @Override
    public Optional<List<Order>> getOrders() {
        return orderDatabase.findAllOrders();
    }

    private int getMaxOrderNumber() {
        int maxOrderNumber = 0;
        var orderList = orderDatabase.findAllOrders();
        if (orderList.isEmpty()){
            return maxOrderNumber;
        }

        maxOrderNumber = orderList.stream()
                .max(Comparator.comparing(orders -> ));
    }
}
