package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartItemDatabase cartItemDatabaseMock;

    @Mock
    private GetLoggedUser getLoggedUserMock;

    @InjectMocks
    private CartService cartService;

    @Test
    void shouldReturnCartItems() {
        //given
        User user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );
        user.setId(1L);
        CartItem cartItem1 = new CartItem();
        List<CartItem> cartItemList = List.of(cartItem1, cartItem1);

        given(getLoggedUserMock.getLoggedUser()).willReturn(Optional.of(user));

        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.of(cartItemList));

        //when
        List<CartItem> result = cartService.handle();

        //then
        assertThat(result, is(cartItemList));
    }

    @Test
    void shouldReturnEmptyOptionalFromNoLoggedUserCartItems() {
        //given
        User user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );
        user.setId(1L);

        given(getLoggedUserMock.getLoggedUser()).willReturn(Optional.of(user));

        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.empty());

        //when
        List<CartItem> result = cartService.handle();

        //then
        System.out.println(result);
    }
}