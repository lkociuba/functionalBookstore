package com.example.functionalBookstore.domain.cart.core.ports.incoming;

import com.example.functionalBookstore.domain.cart.core.model.AddCartItemCommand;

public interface AddNewCartItem {
    void save(AddCartItemCommand addCartItemCommand);
}
