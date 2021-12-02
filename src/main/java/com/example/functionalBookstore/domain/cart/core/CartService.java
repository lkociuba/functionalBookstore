package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.AddCartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBookById;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CartService implements GetLoggedUserCartItems, AddCartItem {

    private final CartItemDatabase cartItemDatabase;
    private final GetLoggedUser getLoggedUser;
    private final GetBookById getBookById;

    @Override
    public List<CartItem> getLoggedUserCartItems() {
        var loggedUserId = getLoggedUser.getLoggedUser().getId();
        return cartItemDatabase.findCartItemsByUser(loggedUserId)
                .orElseGet(ArrayList::new);
    }

    @Override
    public void addCartItem(Long bookId) {
        Book book = getBookById.getBookById(bookId).get();
        CartItem cartItem = checkIfCartItemExistAndReturnCartItemToSave(book);
        cartItemDatabase.save(cartItem);
    }

    private CartItem checkIfCartItemExistAndReturnCartItemToSave(Book book) {
        if (book.getCartItem() != null) {
            return this.increaseExistingCartItemQuantity(book);
        } else {
            return this.createNewCartItem(book);
        }
    }

    private CartItem increaseExistingCartItemQuantity(Book book) {
        CartItem cartItem = book.getCartItem();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        return cartItem;
    }

    private CartItem createNewCartItem(Book book) {
        User user = getLoggedUser.getLoggedUser();
        return new CartItem(
                book, 1, user);
    }
}
