package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.AddCustomerInfoCommand;
import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.*;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CustomerInfoDatabase;
import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBookById;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class CartService implements
        GetLoggedUserCartItems, AddCartItem, DeleteCartItem, IncreaseCartItemQuantity,
        DecreaseCartItemQuantity, GetCartFinalAmount, AddCustomerInfo {

    private final CartItemDatabase cartItemDatabase;
    private final GetLoggedUser getLoggedUser;
    private final GetBookById getBookById;
    private final CustomerInfoDatabase customerInfoDatabase;

    @Override
    public List<CartItem> getLoggedUserCartItems() {
        var loggedUserId = getLoggedUser.getLoggedUser().getId();
        return cartItemDatabase.findCartItemsByUser(loggedUserId)
                .orElseGet(ArrayList::new);
    }

    @Override
    public void addCartItem(Long bookId) {
        if (isIdValueNotNull.test(bookId)) {
            var book = getBookById.getBookById(bookId).orElseThrow();
            var cartItem = checkIfCartItemExistAndReturnCartItemToSave(book);
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
            var cartItem = cartItemDatabase.findCartItemById(cartItemId).orElseThrow();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemDatabase.save(cartItem);
        } else throw new NullPointerException();
    }


    @Override
    public void decreaseCartItemQuantity(Long cartItemId) {
        if (isIdValueNotNull.test(cartItemId)) {
            var cartItem = cartItemDatabase.findCartItemById(cartItemId).orElseThrow();
            if (isQuantityEqualOrLowerThan1.test(cartItem.getQuantity())) {
                this.deleteCartItem(cartItemId);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemDatabase.save(cartItem);
            }
        }
    }

    @Override
    public double calculateCartFinalAmount() {
        return this.getLoggedUserCartItems().stream()
                .mapToDouble(item -> item.getQuantity() * transformPriceToDouble.apply(item.getBook().getPrice()))
                .sum();
    }

    @Override
    public CustomerInfo saveCustomerInfo(AddCustomerInfoCommand command) {
        var user = getLoggedUser.getLoggedUser();
        var customerInfo = new CustomerInfo(
                user,
                command.getCustomerName(),
                command.getCustomerAddress(),
                user.getEmail(),
                command.getCustomerPhone()
        );
        return customerInfoDatabase.save(customerInfo);
    }

    private final Predicate<Long> isIdValueNotNull = Objects::nonNull;
    private final Predicate<Integer> isQuantityEqualOrLowerThan1 =
            quantity -> quantity <= 1;
    private final Function<BigDecimal, Double> transformPriceToDouble = BigDecimal::doubleValue;

    private CartItem checkIfCartItemExistAndReturnCartItemToSave(Book book) {
        if (book.getCartItem() != null) {
            return this.increaseExistingCartItemQuantityFromBook(book);
        } else {
            return this.createNewCartItem(book);
        }
    }

    private CartItem increaseExistingCartItemQuantityFromBook(Book book) {
        var cartItem = book.getCartItem();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        return cartItem;
    }

    private CartItem createNewCartItem(Book book) {
        var user = getLoggedUser.getLoggedUser();
        return new CartItem(
                book, 1, user);
    }
}
