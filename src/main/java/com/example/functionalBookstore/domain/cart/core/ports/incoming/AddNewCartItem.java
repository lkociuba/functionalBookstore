package com.example.functionalBookstore.domain.cart.core.ports.incoming;

public interface AddNewCartItem {
    void save(AddCartItemCommand addCartItemCommand);
}
