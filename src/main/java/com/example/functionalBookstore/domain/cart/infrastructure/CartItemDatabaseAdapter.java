package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CartItemDatabaseAdapter implements CartItemDatabase {

    private final CartItemRepository cartItemRepository;

    @Override
    public Optional<List<CartItem>> findCartItemsByUser(Long loggedUserId) {
        try {
            return Optional.ofNullable(
                    cartItemRepository.findByUserId(loggedUserId));
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Optional<CartItem> findCartItemById(Long cartItemId) {
        try {
            return cartItemRepository.findById(cartItemId);
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
