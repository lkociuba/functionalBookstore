package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerInfoDatabaseAdapterTest {

    @Mock
    private CustomerInfoRepository customerInfoRepoMock;

    @InjectMocks
    private CustomerInfoDatabaseAdapter customerInfoDatabaseAdapter;

    @Test
    void shouldSaveCustomerInfo() {
        customerInfoDatabaseAdapter.save(new CustomerInfo());

        verify(customerInfoRepoMock, times(1)).save(any(CustomerInfo.class));
    }
}