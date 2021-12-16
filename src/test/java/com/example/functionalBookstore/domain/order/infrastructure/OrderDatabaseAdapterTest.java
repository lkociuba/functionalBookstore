package com.example.functionalBookstore.domain.order.infrastructure;

import com.example.functionalBookstore.domain.order.core.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderDatabaseAdapterTest {

    @Mock
    private OrderRepository orderRepositoryMock;

    @InjectMocks
    private OrderDatabaseAdapter orderDatabaseAdapter;

    @Test
    void shouldFinnAllOrdersInFindAllOrders() {
        //given
        var order = new Order();
        List<Order> orderList = List.of(order, order);
        given(orderRepositoryMock.findAll()).willReturn(orderList);

        //when
        Optional<List<Order>> result = orderDatabaseAdapter.findAllOrders();

        //then
        assertThat(result, is(Optional.of(orderList)));
    }

    @Test
    void shouldSaveNewOrder() {
        //when
        orderDatabaseAdapter.save(new Order());

        //then
        verify(orderRepositoryMock, times(1)).save(any(Order.class));
    }
}