package com.example.functionalBookstore.domain.cart.core.model;

public class AddCustomerInfoCommand {

    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public AddCustomerInfoCommand(){}

    public AddCustomerInfoCommand(String customerName, String customerAddress,  String customerPhone) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
