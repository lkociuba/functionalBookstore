package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.inventory.core.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CartItemDatabaseAdapterTest {

    @Mock
    private CartItemRepository cartItemRepoMock;

    @InjectMocks
    private CartItemDatabaseAdapter cartItemDatabaseAdapter;

    @Test
    void shouldGetLoggedUserCartItems() {
        //given
        Book book1 = new Book();
        Book book2 = new Book();
        List<CartItem> cartItemList = List.of(
                new CartItem(book1, 1),
                new CartItem(book2, 2)
        );

        given(cartItemRepoMock.findByUserId(Mockito.anyLong())).willReturn(cartItemList);

        //when
        Optional<List<CartItem>> result = cartItemDatabaseAdapter.getLoggedUserCartItems(1L);

        //then
        assertThat(result, is(Optional.of(cartItemList)));
    }

    @Test
    void shouldReturnEmptyOptionalFromNullLoggedUserId() {
        //given
        given(cartItemRepoMock.findByUserId(Mockito.anyLong())).willReturn(null);

        //when
        Optional<List<CartItem>> result = cartItemDatabaseAdapter.getLoggedUserCartItems(1L);

        //then
        assertThat(result, is(Optional.empty()));
    }

    @Test
    void shouldThrowExceptionFromNullLoggedUserId() {
        //given
        given(cartItemRepoMock.findByUserId(Mockito.anyLong())).willReturn(null);

        assertThrows(Exception.class, () ->
                cartItemDatabaseAdapter.getLoggedUserCartItems(null));
    }
}