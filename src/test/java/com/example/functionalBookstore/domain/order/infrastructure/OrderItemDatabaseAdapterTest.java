package com.example.functionalBookstore.domain.order.infrastructure;

import com.example.functionalBookstore.domain.order.core.model.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderItemDatabaseAdapterTest {

    @Mock
    private OrderItemRepository orderItemRepositoryMock;

    @InjectMocks
    private OrderItemDatabaseAdapter orderItemDatabaseAdapter;

    @Test
    void shouldSaveNewOrderItem() {
        //when
        orderItemDatabaseAdapter.save(new OrderItem());

        //then
        verify(orderItemRepositoryMock, times(1)).save(any(OrderItem.class));
    }
}