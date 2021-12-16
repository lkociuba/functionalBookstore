package com.example.functionalBookstore.infrastructure;

import com.example.functionalBookstore.domain.cart.core.ports.incoming.DeleteCartItemAndCustomerInfoAfterOrderSave;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetCartFinalAmount;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetCustomerInfo;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.order.core.OrderService;
import com.example.functionalBookstore.domain.order.core.ports.incoming.AddNewOrder;
import com.example.functionalBookstore.domain.order.core.ports.incoming.GetOrders;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderDatabase;
import com.example.functionalBookstore.domain.order.core.ports.outgoing.OrderItemDatabase;
import com.example.functionalBookstore.domain.order.infrastructure.OrderDatabaseAdapter;
import com.example.functionalBookstore.domain.order.infrastructure.OrderItemDatabaseAdapter;
import com.example.functionalBookstore.domain.order.infrastructure.OrderItemRepository;
import com.example.functionalBookstore.domain.order.infrastructure.OrderRepository;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderDomainConfig {

    @Bean
    public OrderDatabase orderDatabase(OrderRepository orderRepository) {
        return new OrderDatabaseAdapter(orderRepository);
    }

    @Bean
    public OrderItemDatabase orderItemDatabase(OrderItemRepository orderItemRepository) {
        return new OrderItemDatabaseAdapter(orderItemRepository);
    }

    @Bean
    @Qualifier("AddNewOrder")
    public AddNewOrder addNewOrder(GetLoggedUser getLoggedUser, OrderDatabase orderDatabase,
                                   GetCartFinalAmount getCartFinalAmount, GetCustomerInfo getCustomerInfo,
                                   OrderItemDatabase orderItemDatabase, GetLoggedUserCartItems getLoggedUserCartItems,
                                   DeleteCartItemAndCustomerInfoAfterOrderSave
                                           deleteCartItemAndCustomerInfoAfterOrderSave) {
        return new OrderService(getLoggedUser, orderDatabase, getCartFinalAmount, getCustomerInfo,
                orderItemDatabase, getLoggedUserCartItems, deleteCartItemAndCustomerInfoAfterOrderSave);
    }

    @Bean
    @Qualifier("GetOrders")
    public GetOrders getOrders(GetLoggedUser getLoggedUser, OrderDatabase orderDatabase,
                               GetCartFinalAmount getCartFinalAmount, GetCustomerInfo getCustomerInfo,
                               OrderItemDatabase orderItemDatabase, GetLoggedUserCartItems getLoggedUserCartItems,
                               DeleteCartItemAndCustomerInfoAfterOrderSave
                                       deleteCartItemAndCustomerInfoAfterOrderSave) {
        return new OrderService(getLoggedUser, orderDatabase, getCartFinalAmount, getCustomerInfo,
                orderItemDatabase, getLoggedUserCartItems, deleteCartItemAndCustomerInfoAfterOrderSave);
    }
}
