package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Cart;
import com.app.shopping.ecommerce.entity.Customer;
import com.app.shopping.ecommerce.entity.Product;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.payload.CartDto;
import com.app.shopping.ecommerce.repository.CartRepository;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.repository.ProductRepository;
import com.app.shopping.ecommerce.services.CartService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @MockBean
    private CartRepository cartRepository;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private EmailExtractor emailExtractor;
    MockHttpServletRequest request = new MockHttpServletRequest();
    @BeforeEach
    void setUp() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setQuantity(1);

        Product product = new Product();
        product.setId(1L);
        product.setInventory(10);
        product.setPrice(100);
        product.setQuantity(10);
        product.setDescription("Product 1");
        product.setName("Product 1");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer 1");
        customer.setEmail("test@example.com");

        cart.setProduct(product);
        cart.setCustomer(customer);
        Mockito.when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(cart);
        Mockito.when(cartRepository.findByCustomer(customer)).thenReturn(List.of(cart));
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(customerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(customer));
        Mockito.when(emailExtractor.getEmailFromRequest(Mockito.any(MockHttpServletRequest.class))).thenReturn("test@example.com");
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    }

    @Test
    void addToCart() {
        CartDto cart = new CartDto();
        cart.setId(1L);
        cart.setQuantity(1);
        cart.setProductId(1L);
        CartDto cartDto = cartService.addToCart(cart, request);
        assertEquals(cartDto.getQuantity(), 2); // As I add a condition to increase quantity If quantity already there It will increase by 1
        assertEquals(cartDto.getId(), 1L);
        assertEquals(cartDto.getProductId(), 1L);
    }

    @Test
    void addToCart1(){
        CartDto cartDto = new CartDto();
        cartDto.setId(1L);
        cartDto.setQuantity(11);
        cartDto.setProductId(1L);
        assertThrows(ECommerceApiException.class, () -> cartService.addToCart(cartDto, request));
    }

    @Test
    void addToCart2(){
        CartDto cartDto = new CartDto();
        cartDto.setId(1L);
        cartDto.setQuantity(1);
        cartDto.setProductId(1L);
        Mockito.when(cartRepository.findByCustomer(Mockito.any(Customer.class))).thenReturn(List.of());
        CartDto cart = cartService.addToCart(cartDto, request);
        assertEquals(cart.getQuantity(),1);
    }

    @Test
    void getCartById() {
        CartDto cart = cartService.getCartById(1L, request);
        assertEquals(cart.getId(), 1L);
        assertEquals(cart.getQuantity(), 1);
        assertEquals(cart.getProductId(), 1L);
    }

    @Test
    void getCartById1(){
        Cart cart = new Cart();
        cart.setId(2L);
        cart.setQuantity(1);
        Customer customer = new Customer();
        customer.setId(2L);
        cart.setCustomer(customer);
        Mockito.when(cartRepository.findById(2L)).thenReturn(Optional.of(cart));
        assertThrows(ECommerceApiException.class, () -> cartService.getCartById(2L, request));
    }

    @Test
    void getAllCart() {
        List<CartDto> cartList = cartService.getAllCart(request);
        assertEquals(cartList.size(), 1);
        assertEquals(cartList.get(0).getId(), 1L);
        assertEquals(cartList.get(0).getQuantity(), 1);
    }

    @Test
    void updateCart() {
        CartDto cart = new CartDto();
        cart.setId(1L);
        cart.setQuantity(1);
        cart.setProductId(1L);
        CartDto cartDto = cartService.updateCart(1L, cart, request);
        assertEquals(cartDto.getQuantity(), 1);
        assertEquals(cartDto.getId(), 1L);
        assertEquals(cartDto.getProductId(), 1L);
    }

    @Test
    void updateCart1(){
        CartDto cartDto = new CartDto();
        cartDto.setId(1L);
        cartDto.setQuantity(1);
        cartDto.setProductId(1L);
        Cart cart = new Cart();
        cart.setId(2L);
        cart.setQuantity(1);
        Customer customer = new Customer();
        customer.setId(2L);
        cart.setCustomer(customer);
        Mockito.when(cartRepository.findById(2L)).thenReturn(Optional.of(cart));
        assertThrows(ECommerceApiException.class, () -> cartService.updateCart(2L,cartDto, request));

    }

    @Test
    void deleteCart() {
//        cartService.deleteCart(1L, request);
        assertDoesNotThrow(() -> cartService.deleteCart(1L, request));
    }

    @Test
    void deleteCart1(){
        Cart cart = new Cart();
        cart.setId(2L);
        cart.setQuantity(1);
        Customer customer = new Customer();
        customer.setId(2L);
        cart.setCustomer(customer);
        Mockito.when(cartRepository.findById(2L)).thenReturn(Optional.of(cart));
        assertThrows(ECommerceApiException.class, () -> cartService.deleteCart(2L, request));
    }
}