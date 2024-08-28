package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;
    @BeforeEach
    void setUp() {
        order =new Order(1L,"order","location",1,1.0, new Date(),new Date(), "status",new ArrayList<>(),new Customer());
    }
    @Test
    void getId() {
        assertEquals(1L,order.getId());
    }

    @Test
    void getOrderBy() {
        assertEquals("order",order.getOrderBy());
    }

    @Test
    void getLocation() {
        assertEquals("location",order.getLocation());
    }

    @Test
    void getItems() {
        assertEquals(1,order.getItems());
    }

    @Test
    void getPrice() {
        assertEquals(1.0,order.getPrice());
    }

    @Test
    void getPlacedOn() {
        Date date = new Date();
        order.setPlacedOn(date);
        assertEquals(date,order.getPlacedOn());
    }

    @Test
    void getDeliveryOn() {
        Date date = new Date();
        order.setDeliveryOn(date);
        assertEquals(date,order.getDeliveryOn());
    }

    @Test
    void getStatus() {
        assertEquals("status",order.getStatus());
    }

    @Test
    void getOrderProducts() {
        assertEquals(new ArrayList<>(),order.getOrderProducts());
    }

    @Test
    void getCustomer() {
        Customer customer = new Customer();
        order.setCustomer(customer);
        assertEquals(customer,order.getCustomer());
    }

    @Test
    void setId() {
        order.setId(2L);
        assertEquals(2L,order.getId());
    }

    @Test
    void setOrderBy() {
        order.setOrderBy("order1");
        assertEquals("order1",order.getOrderBy());
    }

    @Test
    void setLocation() {
        order.setLocation("location1");
        assertEquals("location1",order.getLocation());
    }

    @Test
    void setItems() {
        order.setItems(2);
        assertEquals(2,order.getItems());
    }

    @Test
    void setPrice() {
        order.setPrice(2.0);
        assertEquals(2.0,order.getPrice());
    }

    @Test
    void setPlacedOn() {
        Date date = new Date();
        order.setPlacedOn(date);
        assertEquals(date,order.getPlacedOn());
    }

    @Test
    void setDeliveryOn() {
        order.setDeliveryOn(new Date());
        assertEquals(new Date(),order.getDeliveryOn());
    }

    @Test
    void setStatus() {
        order.setStatus("status1");
        assertEquals("status1",order.getStatus());
    }

    @Test
    void setOrderProducts() {
        order.setOrderProducts(new ArrayList<>());
        assertEquals(new ArrayList<>(),order.getOrderProducts());
    }

    @Test
    void setCustomer() {
        Customer customer = new Customer();
        order.setCustomer(customer);
        assertEquals(customer,order.getCustomer());
    }
}