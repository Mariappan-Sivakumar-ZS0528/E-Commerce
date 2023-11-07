package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CustomerDto;
import com.app.shopping.ecommerce.payload.CustomerUpdatePassword;
import com.app.shopping.ecommerce.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto, HttpServletRequest request){
        return ResponseEntity.ok(customerService.updateCustomer(customerDto, request));
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/update/password")
    public ResponseEntity<String> updateCustomerPassword(@RequestBody CustomerUpdatePassword customerUpdatePassword, HttpServletRequest request){
        return ResponseEntity.ok(customerService.updateCustomerPassword(customerUpdatePassword, request));
    }
}
