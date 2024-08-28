package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer;
    Set<AddressBook> addressBooks;
    Set<Cart> carts;
    Set<Order> orders;
    @BeforeEach
    void setUp() {
        addressBooks=new HashSet<>();
        carts=new HashSet<>();
        orders=new HashSet<>();
        customer=new Customer(1L, "name", "email", "number", addressBooks, carts, orders);
    }

    @Test
    void getId() {
        assertEquals(1L, customer.getId());
    }

    @Test
    void getName() {
        assertEquals("name", customer.getName());
    }

    @Test
    void getEmail() {
        assertEquals("email", customer.getEmail());
    }

    @Test
    void getNumber() {
        assertEquals("number", customer.getNumber());
    }

    @Test
    void getAddressBooks() {
        assertEquals(addressBooks, customer.getAddressBooks());
    }

    @Test
    void getCarts() {
        assertEquals(carts, customer.getCarts());
    }

    @Test
    void getOrders() {
        assertEquals(orders, customer.getOrders());
    }

    @Test
    void setId() {
        customer.setId(2L);
        assertEquals(2L, customer.getId());
    }

    @Test
    void setName() {
        customer.setName("name2");
        assertEquals("name2", customer.getName());
    }

    @Test
    void setEmail() {
        customer.setEmail("email2");
        assertEquals("email2", customer.getEmail());
    }

    @Test
    void setNumber() {
        customer.setNumber("number2");
        assertEquals("number2", customer.getNumber());
    }

    @Test
    void setAddressBooks() {
        Set<AddressBook> addressBooks=new HashSet<>();
        customer.setAddressBooks(addressBooks);
        assertEquals(addressBooks, customer.getAddressBooks());
    }

    @Test
    void setCarts() {
        Set<Cart> carts=new HashSet<>();
        customer.setCarts(carts);
        assertEquals(carts, customer.getCarts());
    }

    @Test
    void setOrders() {
        Set<Order> orders=new HashSet<>();
        customer.setOrders(orders);
        assertEquals(orders, customer.getOrders());
    }
}