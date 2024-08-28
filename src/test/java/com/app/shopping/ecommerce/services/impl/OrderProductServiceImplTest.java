package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.*;
import com.app.shopping.ecommerce.payload.AddressBookDto;
import com.app.shopping.ecommerce.payload.CheckoutDto;
import com.app.shopping.ecommerce.repository.CartRepository;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.repository.OrderProductRepository;
import com.app.shopping.ecommerce.repository.OrderRepository;
import com.app.shopping.ecommerce.services.AddressBookService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import io.jsonwebtoken.JwsHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderProductServiceImplTest {
    @Mock
    private OrderProductRepository orderProductRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private EmailExtractor emailExtractor;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AddressBookService addressBookService;
    MockHttpServletRequest request = new MockHttpServletRequest();
    @InjectMocks
    private OrderProductServiceImpl orderProductService;
    AddressBookDto addressBookDto;
    Customer customer;
    Cart cart;
    AddressBook addressBook;
    Product product;
    @BeforeEach
    void setUp() {
        product=new Product();
        product.setId(1L);
        product.setName("name");
        addressBookDto=new AddressBookDto();
        addressBookDto.setId(1L);
        addressBookDto.setAddressLine1("address1");
        addressBookDto.setAddressLine2("address2");
        addressBookDto.setCity("city");
        addressBookDto.setAddressLine3("address3");
        addressBookDto.setPostalCode("1234");
        addressBook = new AddressBook();
        addressBook.setId(1L);
        addressBook.setAddressLine1("address1");
        addressBook.setAddressLine2("address2");
        addressBook.setCity("city");
        addressBook.setAddressLine3("address3");
        addressBook.setPostalCode("1234");
        customer=new Customer();
        customer.setId(1L);
        customer.setEmail("abc@gmail.com");
        customer.setAddressBooks(Set.of(new AddressBook()));
        cart=new Cart();
        cart.setId(1L);
        cart.setQuantity(1);
        cart.setProduct(product);
        cart.setCustomer(customer);
    }

    @Test
    void checkout() {
        Order order=new Order();
        when(addressBookService.getAddressBookById(1L,request)).thenReturn(addressBookDto);
        when(modelMapper.map(addressBookDto, AddressBook.class)).thenReturn(addressBook);
        when(cartRepository.findByProductIdAndCustomerId(1L,1L)).thenReturn(cart);
        when(emailExtractor.getEmailFromRequest(request)).thenReturn("abc@gmail.com");
        when(customerRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.ofNullable(customer));
        when(cartRepository.findAllByCustomerId(1L)).thenReturn(List.of(cart));
        assertEquals("done",orderProductService.checkout(request, new CheckoutDto(1L,new Date())));
    }
}