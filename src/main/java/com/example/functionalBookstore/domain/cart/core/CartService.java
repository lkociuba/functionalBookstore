package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.*;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBookById;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class CartService implements
        GetLoggedUserCartItems, AddCartItem, DeleteCartItem, IncreaseCartItemQuantity, DecreaseCartItemQuantity {

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
        if (isIdValueNotNull.test(bookId)) {
            Book book = getBookById.getBookById(bookId).orElseThrow();
            CartItem cartItem = checkIfCartItemExistAndReturnCartItemToSave(book);
            cartItemDatabase.save(cartItem);
        } else throw new NullPointerException();
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        if (isIdValueNotNull.test(cartItemId)) {
            cartItemDatabase.deleteById(cartItemId);
        } else throw new NullPointerException();
    }

    @Override
    public void increaseCartItemQuantity(Long cartItemId) {
        if (isIdValueNotNull.test(cartItemId)) {
            CartItem cartItem = cartItemDatabase.findCartItemById(cartItemId).orElseThrow();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemDatabase.save(cartItem);
        } else throw new NullPointerException();
    }


    @Override
    public void decreaseCartItemQuantity(Long cartItemId) {
        if (isIdValueNotNull.test(cartItemId)) {
            CartItem cartItem = cartItemDatabase.findCartItemById(cartItemId).orElseThrow();
            if (isQuantityEqualOrLowerThan1.test(cartItem.getQuantity())){
                this.deleteCartItem(cartItemId);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemDatabase.save(cartItem);
            }
        }
    }

    private final Predicate<Long> isIdValueNotNull = Objects::nonNull;
    private final Predicate<Integer> isQuantityEqualOrLowerThan1 =
            quantity -> quantity <= 1;

    private CartItem checkIfCartItemExistAndReturnCartItemToSave(Book book) {
        if (book.getCartItem() != null) {
            return this.increaseExistingCartItemQuantityFromBook(book);
        } else {
            return this.createNewCartItem(book);
        }
    }

    private CartItem increaseExistingCartItemQuantityFromBook(Book book) {
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
