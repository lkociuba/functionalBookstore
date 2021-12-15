package com.example.functionalBookstore.domain.order.core;

import com.example.functionalBookstore.domain.order.core.ports.incoming.AddNewOrder;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderService implements AddNewOrder {

    private final GetLoggedUser getLoggedUser;

    @Override
    public void saveOrder() {
        var user = getLoggedUser.getLoggedUser();

    }

    private int getMaxOrderNumber() {
        int maxOrderNumber = 0;

    }
}
