package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Customer;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.CustomerDto;
import com.app.shopping.ecommerce.payload.CustomerUpdatePassword;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.repository.UserRepository;
import com.app.shopping.ecommerce.services.CustomerService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    EmailExtractor emailExtractor;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    ModelMapper modelMapper;

    HttpServletRequest request=new MockHttpServletRequest();

    CustomerDto customerDto;
    Customer customer;
    User user;
    CustomerUpdatePassword customerUpdatePassword;

    @BeforeEach
    void setUp() {
        customerDto=new CustomerDto(1L, "name", "email", "number");
        customer=new Customer(1L, "name", "email", "number",null,null,null);
        user=new User(1L, "name", "email", "password","number","null",null);
        customerUpdatePassword=new CustomerUpdatePassword(1L,"oldPassword", "newPassword", "newPassword");
        Mockito.when(customerRepository.findByEmail("email")).thenReturn(Optional.of(customer));
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(user));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(emailExtractor.getEmailFromRequest(Mockito.any(HttpServletRequest.class))).thenReturn("email");
        Mockito.when(modelMapper.map(customer, CustomerDto.class)).thenReturn(customerDto);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
    }

    @Test
    void updateCustomerSuccess() {
        assertEquals(customerDto, customerService.updateCustomer(customerDto, request));
    }

    @Test
    void updateCustomerFailureCustomerNotFound() {
        Mockito.when(customerRepository.findByEmail("email")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer(customerDto, request));
    }

    @Test
    void updateCustomerFailureUserNotFound() {
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer(customerDto, request));
    }

    @Test
    void updateCustomerPassword() {
        assertEquals("Password updated successfully", customerService.updateCustomerPassword(customerUpdatePassword, request));
    }


    @Test
    void updateCustomerPasswordFailureOldPasswordNotMatch() {
        Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        assertThrows(ECommerceApiException.class, () -> customerService.updateCustomerPassword(customerUpdatePassword, request));
    }


    @Test
    void updateCustomerPasswordFailureNewPasswordNotMatch() {
        customerUpdatePassword.setNewPassword("newPassword");
        customerUpdatePassword.setConfirmPassword("confirmPassword");
        assertThrows(ECommerceApiException.class, () -> customerService.updateCustomerPassword(customerUpdatePassword, request));
    }

    @Test
    void updateCustomerPasswordFailureNewPasswordSameAsOldPassword() {
        customerUpdatePassword.setNewPassword("password");
        customerUpdatePassword.setConfirmPassword("password");
        customerUpdatePassword.setOldPassword("password");
        assertThrows(ECommerceApiException.class, () -> customerService.updateCustomerPassword(customerUpdatePassword, request));
    }

}