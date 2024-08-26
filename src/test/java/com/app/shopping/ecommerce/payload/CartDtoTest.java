package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartDtoTest {
    CartDto cartDto;

    @BeforeEach
    void setUp() {
        cartDto=new CartDto(1L, 1L, 1);
    }

    @Test
    void getId() {
        assertEquals(1L, cartDto.getId());
    }

    @Test
    void getProductId() {
        assertEquals(1L, cartDto.getProductId());
    }

    @Test
    void getQuantity() {
        assertEquals(1, cartDto.getQuantity());
    }

    @Test
    void setId() {
        cartDto.setId(2L);
        assertEquals(2L, cartDto.getId());
    }

    @Test
    void setProductId() {
        cartDto.setProductId(2L);
        assertEquals(2L, cartDto.getProductId());
    }

    @Test
    void setQuantity() {
        cartDto.setQuantity(2);
        assertEquals(2, cartDto.getQuantity());
    }
}