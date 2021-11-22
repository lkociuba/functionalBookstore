package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
