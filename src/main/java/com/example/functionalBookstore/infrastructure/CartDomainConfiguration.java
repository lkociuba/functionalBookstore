package com.example.functionalBookstore.infrastructure;

import com.example.functionalBookstore.domain.cart.core.CartService;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.DeleteCartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.cart.infrastructure.CartItemDatabaseAdapter;
import com.example.functionalBookstore.domain.cart.infrastructure.CartItemRepository;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBookById;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartDomainConfiguration {

    @Bean
    public CartItemDatabase cartItemDatabase(CartItemRepository cartItemRepository) {
        return new CartItemDatabaseAdapter(cartItemRepository);
    }

    @Bean
    public GetLoggedUserCartItems getLoggedUserCartItems(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById);
    }
}
