package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FullwidthDisplayFeaturedPromosDtoTest {
    FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto;
    Date date = new Date();

    @BeforeEach
    void setUp() {
        fullwidthDisplayFeaturedPromosDto = new FullwidthDisplayFeaturedPromosDto();
        fullwidthDisplayFeaturedPromosDto = new FullwidthDisplayFeaturedPromosDto(1L, "promoTitle", date, date, "link");
    }

    @Test
    void getId() {
        assertEquals(1L, fullwidthDisplayFeaturedPromosDto.getId());
    }

    @Test
    void getPromoTitle() {
        assertEquals("promoTitle", fullwidthDisplayFeaturedPromosDto.getPromoTitle());
    }

    @Test
    void getStartingDate() {
        assertEquals(date, fullwidthDisplayFeaturedPromosDto.getStartingDate());
    }

    @Test
    void getEndingDate() {
        assertEquals(date, fullwidthDisplayFeaturedPromosDto.getEndingDate());
    }

    @Test
    void getLink() {
        assertEquals("link", fullwidthDisplayFeaturedPromosDto.getLink());
    }

    @Test
    void setId() {
        fullwidthDisplayFeaturedPromosDto.setId(2L);
        assertEquals(2L, fullwidthDisplayFeaturedPromosDto.getId());
    }

    @Test
    void setPromoTitle() {
        fullwidthDisplayFeaturedPromosDto.setPromoTitle("promoTitle1");
        assertEquals("promoTitle1", fullwidthDisplayFeaturedPromosDto.getPromoTitle());
    }

    @Test
    void setStartingDate() {
        Date date = new Date();
        fullwidthDisplayFeaturedPromosDto.setStartingDate(date);
        assertEquals(date, fullwidthDisplayFeaturedPromosDto.getStartingDate());
    }

    @Test
    void setEndingDate() {
        Date date = new Date();
        fullwidthDisplayFeaturedPromosDto.setEndingDate(date);
        assertEquals(date, fullwidthDisplayFeaturedPromosDto.getEndingDate());
    }

    @Test
    void setLink() {
        fullwidthDisplayFeaturedPromosDto.setLink("link1");
        assertEquals("link1", fullwidthDisplayFeaturedPromosDto.getLink());
    }
}