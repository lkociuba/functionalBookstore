package com.example.functionalBookstore.domain.cart.core;

import com.example.functionalBookstore.domain.cart.core.model.AddCustomerInfoCommand;
import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CartItemDatabase;
import com.example.functionalBookstore.domain.cart.core.ports.outgoing.CustomerInfoDatabase;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartItemDatabase cartItemDatabaseMock;

    @Mock
    private GetLoggedUser getLoggedUserMock;

    @Mock
    private GetBookById getBookByIdMock;

    @Mock
    private CustomerInfoDatabase customerInfoDatabaseMock;

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

        book = new Book(
                "Geographical Atlas",
                "A lot of usefull maps",
                new BigDecimal(56)
        );

        cartItem = new CartItem(book, 1, user);
        cartItemList = List.of(cartItem, cartItem);
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

    @Test
    void shouldDeleteCartItemInDecreaseCartItemQuantityFromCartItemQuantityLoverThan2() {
        //given
        given(cartItemDatabaseMock.findCartItemById(anyLong())).willReturn(Optional.ofNullable(cartItem));

        //when
        cartService.decreaseCartItemQuantity(anyLong());

        //then
        verify(cartItemDatabaseMock, times(1)).deleteById(anyLong());
    }

    @Test
    void shouldDecreaseCartItemQuantityInDecreaseCartItemQuantityFromCartItemQuantityBiggerThan1() {
        //given
        CartItem cartItemWithQuantity2 = new CartItem(book, 2, user);
        given(cartItemDatabaseMock.findCartItemById(anyLong())).willReturn(Optional.of(cartItemWithQuantity2));

        //when
        cartService.decreaseCartItemQuantity(anyLong());

        //then
        verify(cartItemDatabaseMock, times(1)).save(any(CartItem.class));
    }

    @Test
    void shouldDeleteCartItemInDecreaseCartItemQuantityFromCartItemQuantityEqual0() {
        //given
        CartItem cartItemWithQuantity0 = new CartItem(book, 0, user);
        given(cartItemDatabaseMock.findCartItemById(anyLong())).willReturn(Optional.ofNullable(cartItem));

        //when
        cartService.decreaseCartItemQuantity(anyLong());

        //then
        verify(cartItemDatabaseMock, times(1)).deleteById(anyLong());
    }

    @Test
    void shouldDeleteCartItemInDecreaseCartItemQuantityFromCartItemQuantityEqualMinus1() {
        //given
        CartItem cartItemWithQuantity0 = new CartItem(book, -1, user);
        given(cartItemDatabaseMock.findCartItemById(anyLong())).willReturn(Optional.ofNullable(cartItem));

        //when
        cartService.decreaseCartItemQuantity(anyLong());

        //then
        verify(cartItemDatabaseMock, times(1)).deleteById(anyLong());
    }

    @Test
    void shouldReturn112InCalculateCartFinalAmountFrom2BooksPrice56() {
        //given
        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.ofNullable(cartItemList));
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);

        //when
        var result = cartService.calculateCartFinalAmount();

        //then
        assertThat(result, is(112.0));
    }

    @Test
    void shouldReturn0InCalculateCartFinalAmountFrom10BooksPrice0() {
        //given
        Book book = new Book("New Book", "Description", BigDecimal.valueOf(0));
        CartItem testCartItem = new CartItem(book, 10, user);
        List<CartItem> testCartItemList = List.of(testCartItem);

        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.of(testCartItemList));
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);

        //when
        var result = cartService.calculateCartFinalAmount();

        //then
        assertThat(result, is(0.0));
    }

    @Test
    void shouldReturn0InCalculateCartFinalAmountFromEmptyCartItemList() {
        //given
        List<CartItem> testCartItemList = new ArrayList<>();

        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.of(testCartItemList));
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);

        //when
        var result = cartService.calculateCartFinalAmount();

        //then
        assertThat(result, is(0.0));
    }

    @Test
    void shouldReturnSavedCustomerInfo() {
        //given
        var addCustomerInfoCommand = new AddCustomerInfoCommand(
                "Customer name",
                "Customer Address",
                "customerPhone"
        );
        var customerInfo = new CustomerInfo(
                user,
                addCustomerInfoCommand.getCustomerName(),
                addCustomerInfoCommand.getCustomerAddress(),
                user.getEmail(),
                addCustomerInfoCommand.getCustomerPhone()
        );
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);
        given(customerInfoDatabaseMock.save(any(CustomerInfo.class))).willReturn(customerInfo);

        //when
        CustomerInfo result = cartService.saveCustomerInfo(addCustomerInfoCommand);

        //then
        assertThat(result, is(customerInfo));
    }

    @Test
    void shouldReturnCustomerInfoInGetCustomerInfo() {
        //given
        var customerInfo = new CustomerInfo();
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);
        given(customerInfoDatabaseMock.findCustomerInfoByUser(anyLong())).willReturn(Optional.of(customerInfo));

        //when
        CustomerInfo result = cartService.getCustomerInfo();

        //then
        assertThat(result, is(customerInfo));
    }

    @Test
    void shouldThrowNullPointerExceptionInGetCustomerInfoFromNullUser() {
        assertThrows(NullPointerException.class, () ->
                cartService.getCustomerInfo());
    }

    @Test
    void shouldDeleteCartItemAndCustomerInfoAfterOrderSave() {
        //given
        var customerInfo = new CustomerInfo();
        given(getLoggedUserMock.getLoggedUser()).willReturn(user);
        given(customerInfoDatabaseMock.findCustomerInfoByUser(anyLong())).willReturn(Optional.of(customerInfo));
        given(cartItemDatabaseMock.findCartItemsByUser(anyLong())).willReturn(Optional.of(cartItemList));

        //when
        cartService.deleteCartItemAndCustomerInfoAfterOrderSave();

        //then
        verify(customerInfoDatabaseMock,times(1)).delete(customerInfo);
        verify(cartItemDatabaseMock,times(2)).deleteById(anyLong());
    }
}