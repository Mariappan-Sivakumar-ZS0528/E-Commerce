package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderProductTest {
    OrderProduct orderProduct;
    Order order;
    Product product;
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setPrice(1.0);
        order =new Order(1L,"order","location",1,1.0, new Date(),new Date(), "status",new ArrayList<>(),new Customer());
        orderProduct = new OrderProduct(1L,order,product,1,1L);
    }

    @Test
    void getId() {
        assertEquals(1L,orderProduct.getId());
    }

    @Test
    void getOrder() {
        assertEquals(order,orderProduct.getOrder());
    }

    @Test
    void getProduct() {
        assertEquals(product,orderProduct.getProduct());
    }

    @Test
    void getQuantity() {
        assertEquals(1,orderProduct.getQuantity());
    }

    @Test
    void getPrice() {
        assertEquals(1.0,orderProduct.getPrice());
    }

    @Test
    void setId() {
        orderProduct.setId(2L);
        assertEquals(2L,orderProduct.getId());
    }

    @Test
    void setOrder() {
        orderProduct.setOrder(order);
        assertEquals(order,orderProduct.getOrder());
    }

    @Test
    void setProduct() {
        orderProduct.setProduct(product);
        assertEquals(product,orderProduct.getProduct());
    }

    @Test
    void setQuantity() {
        orderProduct.setQuantity(2);
        assertEquals(2,orderProduct.getQuantity());
    }

    @Test
    void setPrice() {
        orderProduct.setPrice(2.0);
        assertEquals(2.0,orderProduct.getPrice());
    }
}