package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.CustomerDto;
import com.app.shopping.ecommerce.payload.CustomerUpdatePassword;
import jakarta.servlet.http.HttpServletRequest;

public interface CustomerService {
    CustomerDto updateCustomer(CustomerDto customerDto, HttpServletRequest request);
    String updateCustomerPassword(CustomerUpdatePassword customerUpdatePassword, HttpServletRequest request);
}
