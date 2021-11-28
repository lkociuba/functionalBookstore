package com.example.functionalBookstore.domain.cart.core.ports.incoming;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;

import java.util.List;

public interface GetLoggedUserCartItems {
    List<CartItem> getLoggedUserCartItems();
}