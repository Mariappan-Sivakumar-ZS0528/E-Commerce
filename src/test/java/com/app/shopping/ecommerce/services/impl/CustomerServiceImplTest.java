package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Customer;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.payload.CustomerDto;
import com.app.shopping.ecommerce.payload.CustomerUpdatePassword;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.repository.UserRepository;
import com.app.shopping.ecommerce.services.CustomerService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    UserRepository userRepository;

    MockHttpServletRequest request = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Mariappan Sivakumar");
        customer.setEmail("spotmari@gmail.com");
        customer.setNumber("1234567890");
        User user = new User();
        user.setId(1L);
        user.setName("Mariappan Sivakumar");
        user.setEmail("spotmari@gmail.com");
        user.setContact("1234567890");
        user.setPassword("$2a$10$uGi4dKpc0QiyK5655n2riObSU5KY8FDP0BtPDJRu96yq7xQJXmex2");
        Mockito.when(customerRepository.findByEmail("spotmari@gmail.com")).thenReturn(Optional.of(customer));
        Mockito.when(userRepository.findByEmail("spotmari@gmail.com")).thenReturn(Optional.of(user));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzcG90bWFyaUBnbWFpbC5jb20iLCJpYXQiOjE2OTkzNTk1ODksImV4cCI6MTY5OTk2NDM4OX0.rVQeSIQpNmFWFqSOPkN4ZkvGJG3aRrcpyA9TFbx0nIHRl069fT7W54PUUQDeJqir");
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Mariappan Sivakumar");
        customerDto.setEmail("mariappan.sivakumar@gmail.com");
        customerDto.setNumber("1234567890");
        CustomerDto customerDto1 = customerService.updateCustomer(customerDto, request);
        assertEquals("Mariappan Sivakumar", customerDto1.getName());
    }

    @Test
    void updateCustomerPassword() {
        CustomerUpdatePassword customerUpdatePassword = new CustomerUpdatePassword();
        customerUpdatePassword.setOldPassword("mariappan");
        customerUpdatePassword.setNewPassword("123456789");
        customerUpdatePassword.setConfirmPassword("123456789");
        String customerUpdatePassword1 = customerService.updateCustomerPassword(customerUpdatePassword, request);
        assertEquals("Password updated successfully", customerUpdatePassword1);
    }
}