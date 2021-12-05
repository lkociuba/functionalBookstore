package com.example.functionalBookstore.domain.cart.infrastructure;

import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
        //when
        customerInfoDatabaseAdapter.save(new CustomerInfo());

        //then
        verify(customerInfoRepoMock, times(1)).save(any(CustomerInfo.class));
    }

    @Test
    void shouldReturnCustomerInfoInFindCustomerInfoByUser() {
        //given
        var customerInfo = new CustomerInfo();
        given(customerInfoRepoMock.findByUserId(anyLong())).willReturn(customerInfo);

        //when
        Optional<CustomerInfo> result = customerInfoDatabaseAdapter.findCustomerInfoByUser(anyLong());

        //then
        assertThat(result, is(Optional.of(customerInfo)));
    }

    @Test
    void shouldReturnEmptyOptionalInFindCustomerInfoByUserFromNoDatabaseConnection() {
        //when
        Optional<CustomerInfo> result = customerInfoDatabaseAdapter.findCustomerInfoByUser(anyLong());

        //then
        assertThat(result, is(Optional.empty()));
    }
}