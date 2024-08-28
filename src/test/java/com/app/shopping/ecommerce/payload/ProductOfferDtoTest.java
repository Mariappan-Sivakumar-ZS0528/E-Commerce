package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductOfferDtoTest {
    ProductOfferDto productOfferDto;
    Date startingDate = new Date();
    Date endingDate = new Date();
    @BeforeEach
    void setUp() {
        productOfferDto = new ProductOfferDto(1L,true,"day",startingDate,endingDate,10);
    }

    @Test
    void getId() {
        assertEquals(1L,productOfferDto.getId());
    }

    @Test
    void isActive() {
        assertTrue(productOfferDto.isActive());
    }

    @Test
    void getOfferDurationType() {
        assertEquals("day",productOfferDto.getOfferDurationType());
    }

    @Test
    void getStartingDate() {
        assertEquals(startingDate,productOfferDto.getStartingDate());
    }

    @Test
    void getEndingDate() {
        assertEquals(endingDate,productOfferDto.getEndingDate());
    }

    @Test
    void getDiscount() {
        assertEquals(10,productOfferDto.getDiscount());
    }

    @Test
    void setId() {
        productOfferDto.setId(2L);
        assertEquals(2L,productOfferDto.getId());
    }

    @Test
    void setActive() {
        productOfferDto.setActive(false);
        assertFalse(productOfferDto.isActive());
    }

    @Test
    void setOfferDurationType() {
        productOfferDto.setOfferDurationType("week");
        assertEquals("week",productOfferDto.getOfferDurationType());
    }

    @Test
    void setStartingDate() {
        Date date = new Date();
        productOfferDto.setStartingDate(date);
        assertEquals(date,productOfferDto.getStartingDate());
    }

    @Test
    void setEndingDate() {
        Date date = new Date();
        productOfferDto.setEndingDate(date);
        assertEquals(date,productOfferDto.getEndingDate());
    }

    @Test
    void setDiscount() {
        productOfferDto.setDiscount(20);
        assertEquals(20,productOfferDto.getDiscount());
    }
}