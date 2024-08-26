package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart cart;
    Customer customer;
    Product product;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        product = new Product();
        cart = new Cart(1L, customer, product, 1);
    }

    @Test
    void getId() {
        assertEquals(1L, cart.getId());
    }

    @Test
    void getCustomer() {
        assertEquals(customer, cart.getCustomer());
    }

    @Test
    void getProduct() {
        assertEquals(product, cart.getProduct());
    }

    @Test
    void getQuantity() {
        assertEquals(1, cart.getQuantity());
    }

    @Test
    void setId() {
        cart.setId(2L);
        assertEquals(2L, cart.getId());
    }

    @Test
    void setCustomer() {
        cart.setCustomer(customer);
        assertEquals(customer, cart.getCustomer());
    }

    @Test
    void setProduct() {
        cart.setProduct(product);
        assertEquals(product, cart.getProduct());
    }

    @Test
    void setQuantity() throws NoSuchMethodException {
        cart.setQuantity(2);
        assertEquals(2, cart.getQuantity());
    }
}