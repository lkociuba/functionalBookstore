package com.example.functionalBookstore.domain.cart.core.ports.incoming;

import com.example.functionalBookstore.domain.cart.core.model.AddCustomerInfoCommand;
import com.example.functionalBookstore.domain.cart.core.model.CustomerInfo;

public interface AddCustomerInfo {
    CustomerInfo saveCustomerInfo(AddCustomerInfoCommand addCustomerInfoCommand);
}
