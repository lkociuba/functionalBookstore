package com.example.functionalBookstore.infrastructure;

import com.example.functionalBookstore.domain.cart.core.CartService;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.*;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CustomerInfoDatabase;
import com.example.functionalBookstore.domain.cart.infrastructure.CartItemDatabaseAdapter;
import com.example.functionalBookstore.domain.cart.infrastructure.CartItemRepository;
import com.example.functionalBookstore.domain.cart.infrastructure.CustomerInfoDatabaseAdapter;
import com.example.functionalBookstore.domain.cart.infrastructure.CustomerInfoRepository;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBookById;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartDomainConfiguration {

    @Bean
    public CartItemDatabase cartItemDatabase(CartItemRepository cartItemRepository) {
        return new CartItemDatabaseAdapter(cartItemRepository);
    }

    @Bean
    public CustomerInfoDatabase customerInfoDatabase(CustomerInfoRepository customerInfoRepository) {
        return new CustomerInfoDatabaseAdapter(customerInfoRepository);
    }

    @Bean
    @Qualifier("GetLoggedUserCartItems")
    public GetLoggedUserCartItems getLoggedUserCartItems(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("AddCartItem")
    public AddCartItem addCartItem(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("AddCustomerInfo")
    public AddCustomerInfo addCustomerInfo(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("DecreaseCartItemQuantity")
    public DecreaseCartItemQuantity decreaseCartItemQuantity(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("DeleteCartItem")
    public DeleteCartItem deleteCartItem(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("DeleteCartItemAndCustomerInfoAfterSave")
    public DeleteCartItemAndCustomerInfoAfterOrderSave deleteCartItemAndCustomerInfoAfterOrderSave(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("GetCartFinalAmount")
    public GetCartFinalAmount getCartFinalAmount(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("GetCustomerInfo")
    public GetCustomerInfo getCustomerInfo(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }

    @Bean
    @Qualifier("IncreaseCartItemQuantity")
    public IncreaseCartItemQuantity increaseCartItemQuantity(
            CartItemDatabase cartItemDatabase, GetLoggedUser getLoggedUser,
            GetBookById getBookById, CustomerInfoDatabase customerInfoDatabase) {
        return new CartService(cartItemDatabase, getLoggedUser, getBookById, customerInfoDatabase);
    }
}
