package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FullwidthDisplayFeaturedPromosTest {
    FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos;
    Date date=new Date();
    byte[] bytes=new byte[1];
    @BeforeEach
    void setUp() {
        fullwidthDisplayFeaturedPromos = new FullwidthDisplayFeaturedPromos(1L, "displayPromoTitle", date, date, "link", bytes, 1080, 920, true, bytes, 100, 100);
    }

    @Test
    void getId() {
        assertEquals(1L, fullwidthDisplayFeaturedPromos.getId());
    }

    @Test
    void getPromoTitle() {
        assertEquals("displayPromoTitle", fullwidthDisplayFeaturedPromos.getPromoTitle());
    }

    @Test
    void getStartingDate() {
        assertEquals(date, fullwidthDisplayFeaturedPromos.getStartingDate());
    }

    @Test
    void getEndingDate() {
        assertEquals(date, fullwidthDisplayFeaturedPromos.getEndingDate());
    }

    @Test
    void getLink() {
        assertEquals("link", fullwidthDisplayFeaturedPromos.getLink());
    }

    @Test
    void getDesktopImage() {
        assertEquals(bytes, fullwidthDisplayFeaturedPromos.getDesktopImage());
    }

    @Test
    void getDesktopwidth() {
        assertEquals(1080, fullwidthDisplayFeaturedPromos.getDesktopwidth());
    }

    @Test
    void getDesktopheight() {
        assertEquals(920, fullwidthDisplayFeaturedPromos.getDesktopheight());
    }

    @Test
    void isStatus() {
        assertTrue(fullwidthDisplayFeaturedPromos.isStatus());
    }

    @Test
    void getMobileImage() {
        assertEquals(bytes, fullwidthDisplayFeaturedPromos.getMobileImage());
    }

    @Test
    void getMobilewidth() {
        assertEquals(100, fullwidthDisplayFeaturedPromos.getMobilewidth());
    }

    @Test
    void getMobileheight() {
        assertEquals(100, fullwidthDisplayFeaturedPromos.getMobileheight());
    }

    @Test
    void setId() {
        fullwidthDisplayFeaturedPromos.setId(2L);
        assertEquals(2L, fullwidthDisplayFeaturedPromos.getId());
    }

    @Test
    void setPromoTitle() {
        fullwidthDisplayFeaturedPromos.setPromoTitle("displayPromoTitle2");
        assertEquals("displayPromoTitle2", fullwidthDisplayFeaturedPromos.getPromoTitle());
    }

    @Test
    void setStartingDate() {
        Date date=new Date();
        fullwidthDisplayFeaturedPromos.setStartingDate(date);
        assertEquals(date, fullwidthDisplayFeaturedPromos.getStartingDate());
    }

    @Test
    void setEndingDate() {
        Date date=new Date();
        fullwidthDisplayFeaturedPromos.setEndingDate(date);
        assertEquals(date, fullwidthDisplayFeaturedPromos.getEndingDate());
    }

    @Test
    void setLink() {
        fullwidthDisplayFeaturedPromos.setLink("link2");
        assertEquals("link2", fullwidthDisplayFeaturedPromos.getLink());
    }

    @Test
    void setDesktopImage() {
        byte[] bytes=new byte[1];
        fullwidthDisplayFeaturedPromos.setDesktopImage(bytes);
        assertEquals(bytes, fullwidthDisplayFeaturedPromos.getDesktopImage());
    }

    @Test
    void setDesktopwidth() {
        fullwidthDisplayFeaturedPromos.setDesktopwidth(1081);
        assertEquals(1081, fullwidthDisplayFeaturedPromos.getDesktopwidth());
    }

    @Test
    void setDesktopheight() {
        fullwidthDisplayFeaturedPromos.setDesktopheight(921);
        assertEquals(921, fullwidthDisplayFeaturedPromos.getDesktopheight());
    }

    @Test
    void setStatus() {
        fullwidthDisplayFeaturedPromos.setStatus(false);
        assertFalse(fullwidthDisplayFeaturedPromos.isStatus());
    }

    @Test
    void setMobileImage() {
        byte[] bytes=new byte[1];
        fullwidthDisplayFeaturedPromos.setMobileImage(bytes);
        assertEquals(bytes, fullwidthDisplayFeaturedPromos.getMobileImage());
    }

    @Test
    void setMobilewidth() {
        fullwidthDisplayFeaturedPromos.setMobilewidth(101);
        assertEquals(101, fullwidthDisplayFeaturedPromos.getMobilewidth());
    }

    @Test
    void setMobileheight() {
        fullwidthDisplayFeaturedPromos.setMobileheight(101);
        assertEquals(101, fullwidthDisplayFeaturedPromos.getMobileheight());
    }
}