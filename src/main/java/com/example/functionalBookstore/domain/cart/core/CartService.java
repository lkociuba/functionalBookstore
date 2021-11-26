package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class CartService implements GetLoggedUserCartItems {

    private final CartItemDatabase cartItemDatabase;

    private final GetLoggedUser getLoggedUser;

    @Override
    public List<CartItem> handle() {
        var loggedUserId = getLoggedUser.getLoggedUser().get().getId();
        return cartItemDatabase.findCartItemsByUser(loggedUserId).get();
    }
}
