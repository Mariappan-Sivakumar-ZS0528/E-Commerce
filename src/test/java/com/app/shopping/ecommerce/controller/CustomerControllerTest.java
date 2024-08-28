package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CustomerDto;
import com.app.shopping.ecommerce.payload.CustomerUpdatePassword;
import com.app.shopping.ecommerce.services.CustomerService;
import com.app.shopping.ecommerce.services.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @InjectMocks
    CustomerController customerController;
    @Mock
    CustomerServiceImpl customerService;
    CustomerUpdatePassword customerUpdatePassword;

    CustomerDto customerDto;
    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);

    @BeforeEach
    void setUp() {
        customerDto=new CustomerDto();
        Mockito.when(customerService.updateCustomer(customerDto, request)).thenReturn(customerDto);
        Mockito.when(customerService.updateCustomerPassword(customerUpdatePassword, request)).thenReturn("Password updated successfully");
    }


    @WithMockUser(roles = "USER")
    @Test
    void updateCustomer() {
        ResponseEntity<CustomerDto> responseEntity = customerController.updateCustomer(customerDto, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerDto, responseEntity.getBody());
    }

    @WithMockUser(roles = "USER")
    @Test
    void updateCustomerPassword() {
        ResponseEntity<String> responseEntity = customerController.updateCustomerPassword(customerUpdatePassword, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Password updated successfully", responseEntity.getBody());
    }
}