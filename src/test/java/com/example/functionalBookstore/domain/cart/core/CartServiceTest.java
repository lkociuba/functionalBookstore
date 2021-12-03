package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBookById;
import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartItemDatabase cartItemDatabaseMock;

    @Mock
    private GetLoggedUser getLoggedUserMock;

    @Mock
    private GetBookById getBookByIdMock;

    @InjectMocks
    private CartService cartService;

    private User user;
    private CartItem cartItem;
    private List<CartItem> cartItemList;
    private Book book;

    @BeforeEach
    public void init() {
        user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );
        user.setId(1L);

        cartItem = new CartItem();
        cartItemList = List.of(cartItem, cartItem);

        book = new Book(
                "Geographical Atlas",
                "A lot of usefull maps",
                new BigDecimal(56)
        );
    }

    @Test
    void shouldReturnCartItems() {
        //given
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);
        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.of(cartItemList));

        //when
        List<CartItem> result = cartService.getLoggedUserCartItems();

        //then
        assertThat(result, is(cartItemList));
    }

    @Test
    void shouldReturnNewArrayListFromNoLoggedUserCartItems() {
        //given
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);
        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.empty());

        //when
        List<CartItem> result = cartService.getLoggedUserCartItems();

        //then
        assertThat(result, is(new ArrayList<>()));
    }

    @Test
    void shouldSaveCartItemWithIncrementedQuantityFromBookId() {
        //given
        given(getBookByIdMock.getBookById(1L)).willReturn(Optional.of(book));
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);

        //when
        cartService.addCartItem(1L);

        //then
        verify(cartItemDatabaseMock, times(1)).save(any(CartItem.class));
    }

    @Test
    void shouldThrowNullPointerExceptionInSaveMethodFromNullCartItemId() {
        assertThrows(NullPointerException.class, () ->
                cartService.addCartItem(null));
    }

    @Test
    void shouldDeleteCartItemFromCartItemId() {
        //when
        cartService.deleteCartItem(anyLong());

        //then
        verify(cartItemDatabaseMock, times(1)).deleteById(anyLong());
    }

    @Test
    void shouldThrowNullPointerExceptionFromNullCartItemId() {
        assertThrows(NullPointerException.class, () ->
                cartService.deleteCartItem(null));
    }

    @Test
    void shouldIncreaseCartItemQuantityFromCartItemId() {
        //given
        given(cartItemDatabaseMock.findCartItemById(anyLong())).willReturn(Optional.ofNullable(cartItem));

        //when
        cartService.increaseCartItemQuantity(anyLong());

        //then
        verify(cartItemDatabaseMock, times(1)).save(any(CartItem.class));
    }

    @Test
    void shouldThrowNullPointerExceptionFromNullCartItemInIncreaseCartItemQuantity() {
        assertThrows(NullPointerException.class, () ->
                cartService.increaseCartItemQuantity(null));
    }
}