package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.AddCartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.user.core.model.User;
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
        var loggedUserId = getLoggedUser.getLoggedUser().getId();
        return cartItemDatabase.findCartItemsByUser(loggedUserId)
                .orElseGet(ArrayList::new);
    }

    @Override
    public void addCartItem(Long bookId) {
/*
        //check if any CartItem contain book from bookId
        //function interface - using getLoggedUser
        List<CartItem> cartItems = getLoggedUserCartItems();
        CartItem cartItem = new CartItem();
        for (CartItem item : cartItems) {
            if (item.getBook().getBookId().equals(bookId)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else {
                cartItem = this.createNewCartItem;
            }
        }
        cartItemDatabase.save(cartItem);

 */
    }

    private void createNewCartItem(Long bookId) {
        User user = getLoggedUser.getLoggedUser();

    }
}
