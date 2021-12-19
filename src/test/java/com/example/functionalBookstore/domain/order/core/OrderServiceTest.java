package com.example.functionalBookstore.domain.order.core;

import com.example.functionalBookstore.domain.cart.core.model.CartItem;
import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.DeleteCartItemAndCustomerInfoAfterOrderSave;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetCartFinalAmount;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetCustomerInfo;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.order.core.model.Order;
import com.example.functionalBookstore.domain.order.core.model.OrderItem;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderDatabase;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderItemDatabase;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private GetLoggedUser getLoggedUserMock;
    @Mock
    private OrderDatabase orderDatabaseMock;
    @Mock
    private GetCartFinalAmount getCartFinalAmountMock;
    @Mock
    private GetCustomerInfo getCustomerInfoMock;
    @Mock
    private OrderItemDatabase orderItemDatabaseMock;
    @Mock
    private GetLoggedUserCartItems getLoggedUserCartItemsMock;
    @Mock
    private DeleteCartItemAndCustomerInfoAfterOrderSave deleteCartItemAndCustomerInfoAfterOrderSaveMock;

    @InjectMocks
    private OrderService orderService;

    private final BigDecimal calculatedPrice = BigDecimal.valueOf(10);

    private User loggedUser;
    private List<CartItem> loggedUserCartItemList;
    private CustomerInfo customerInfo;
    private List<Order> loggedUserOrderList;

    @BeforeEach
    public void init() {
        loggedUser = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER"))));
        loggedUser.setId(1L);

        Book book = new Book(
                "Geographical Atlas",
                "A lot of usefull maps",
                new BigDecimal(56));

        CartItem cartItem = new CartItem(book, 1, loggedUser);
        loggedUserCartItemList = List.of(cartItem, cartItem);

        customerInfo = new CustomerInfo(
                loggedUser,
                "John Wick",
                "address",
                "john@email.com",
                "111");

        Order order = new Order(
                loggedUser,
                1,
                calculatedPrice,
                customerInfo);

        loggedUserOrderList = List.of(order);
    }

    @Test
    void shouldReturnAllOrdersInGetOrders() {
        //given
        given(orderDatabaseMock.findAllOrders()).willReturn(Optional.of(loggedUserOrderList));

        //when
        Optional<List<Order>> result = orderService.getOrders();

        //when
        assertThat(result, is(Optional.of(loggedUserOrderList)));
    }

    @Test
    void shouldReturnEmptyOptionalInGetOrdersFromNoDatabaseConnection() {
        //when
        Optional<List<Order>> result = orderService.getOrders();

        //when
        assertThat(result, is(Optional.empty()));
    }

    @Test
    void shouldSaveNewOrderOrderItemsAndDeleteCartItemAndCustomerInfoInSaveOrder() {
        //given
        given(getLoggedUserMock.getLoggedUser()).willReturn(loggedUser);
        given(orderDatabaseMock.findAllOrders()).willReturn(Optional.of(loggedUserOrderList));
        double cartFinalAmount = 10;
        given(getCartFinalAmountMock.calculateCartFinalAmount()).willReturn(cartFinalAmount);
        given(getCustomerInfoMock.getCustomerInfo()).willReturn(customerInfo);
        given(getLoggedUserCartItemsMock.getLoggedUserCartItems()).willReturn(loggedUserCartItemList);

        //when
        orderService.saveOrder();

        //then
        verify(orderDatabaseMock,times(1)).save(any(Order.class));
        verify(orderItemDatabaseMock, times(2)).save(any(OrderItem.class));
        verify(deleteCartItemAndCustomerInfoAfterOrderSaveMock, times(1)).
                deleteCartItemAndCustomerInfoAfterOrderSave();
    }
}