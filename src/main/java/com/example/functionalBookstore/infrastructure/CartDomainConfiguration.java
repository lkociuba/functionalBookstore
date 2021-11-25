package com.example.functionalBookstore.infrastructure;

import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.cart.infrastructure.CartItemDatabaseAdapter;
import com.example.functionalBookstore.domain.cart.infrastructure.CartItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartDomainConfiguration {

    @Bean
    public CartItemDatabase cartItemDatabase (CartItemRepository cartItemRepository){
        return new CartItemDatabaseAdapter(cartItemRepository);
    }
}
