package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.AddCartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CartService implements GetLoggedUserCartItems, AddCartItem {

    private final CartItemDatabase cartItemDatabase;

    private final GetLoggedUser getLoggedUser;

    @Override
    public List<CartItem> getLoggedUserCartItems() {
        return cartItemDatabase.findCartItemsByUser(getLoggedUserId())
                .orElseGet(ArrayList::new);
    }

    @Override
    public void addCartItem(Long bookId) {

    }

    private Long getLoggedUserId() {
        return getLoggedUser.getLoggedUser().getId();
    }
}
