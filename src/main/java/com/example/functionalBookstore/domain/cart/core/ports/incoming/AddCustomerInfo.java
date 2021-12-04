package com.example.functionalBookstore.domain.cart.core.ports.incoming;

import com.example.functionalBookstore.domain.cart.core.model.AddCustomerInfoCommand;

public interface AddCustomerInfo {
    void saveCustomerInfo(AddCustomerInfoCommand addCustomerInfoCommand);
}
