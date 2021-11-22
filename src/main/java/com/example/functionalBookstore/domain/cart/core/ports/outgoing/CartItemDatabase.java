package com.example.functionalBookstore.domain.cart.core.ports.outgoing;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;

import java.util.List;

public interface CartItemDatabase {
    List<CartItem> getCartItems(Long loggedUserId);
}
