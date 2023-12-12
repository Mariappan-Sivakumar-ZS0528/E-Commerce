package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Customer;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.CustomerDto;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.payload.CustomerUpdatePassword;
import com.app.shopping.ecommerce.repository.UserRepository;
import com.app.shopping.ecommerce.services.CustomerService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private EmailExtractor emailExtractor;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, EmailExtractor emailExtractor, ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.emailExtractor = emailExtractor;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setNumber(customerDto.getNumber());
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User", "email", email));
        user.setName(customerDto.getName());
        user.setEmail(customerDto.getEmail());
        user.setContact(customerDto.getNumber());
        userRepository.save(user);
        return modelMapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    @Override
    public String updateCustomerPassword(CustomerUpdatePassword customerUpdatePassword, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User", "email", email));
        if (! passwordEncoder.matches(customerUpdatePassword.getOldPassword(), user.getPassword())) {
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Old password does not match");
        }
        if (!customerUpdatePassword.getNewPassword().equals(customerUpdatePassword.getConfirmPassword())){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        if (customerUpdatePassword.getNewPassword().equals(customerUpdatePassword.getOldPassword())){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "New password cannot be same as old password");
        }
        user.setPassword(passwordEncoder.encode(customerUpdatePassword.getNewPassword()));
        userRepository.save(user);
        return "Password updated successfully";
    }
}
