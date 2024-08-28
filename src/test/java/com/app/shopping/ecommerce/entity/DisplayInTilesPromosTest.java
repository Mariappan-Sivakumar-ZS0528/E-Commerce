package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DisplayInTilesPromosTest {

    DisplayInTilesPromos displayInTilesPromos;
    byte[] bytes=new byte[1];
    Date date=new Date();

    @BeforeEach
    void setUp() {
        displayInTilesPromos = new DisplayInTilesPromos(1L, "displayPromoTitle", date, date, "link", bytes, 1080, 920, true, bytes, 100, 100);
    }

    @Test
    void getId() {
        assertEquals(1L, displayInTilesPromos.getId());
    }

    @Test
    void getDisplayPromoTitle() {
        assertEquals("displayPromoTitle", displayInTilesPromos.getDisplayPromoTitle());
    }

    @Test
    void getStartingDate() {
        assertEquals(date, displayInTilesPromos.getStartingDate());
    }

    @Test
    void getEndingDate() {
        assertEquals(date,displayInTilesPromos.getEndingDate());
    }

    @Test
    void getLink() {
        assertEquals("link", displayInTilesPromos.getLink());
    }

    @Test
    void getDesktopImage() {
        assertEquals(bytes, displayInTilesPromos.getDesktopImage());
    }

    @Test
    void getDesktopwidth() {
        assertEquals(1080, displayInTilesPromos.getDesktopwidth());
    }

    @Test
    void getDesktopheight() {
        assertEquals(920, displayInTilesPromos.getDesktopheight());
    }

    @Test
    void isStatus() {
        assertEquals(true, displayInTilesPromos.isStatus());
    }

    @Test
    void getMobileImage() {
        assertEquals(bytes, displayInTilesPromos.getMobileImage());
    }

    @Test
    void getMobilewidth() {
        assertEquals(100, displayInTilesPromos.getMobilewidth());
    }

    @Test
    void getMobileheight() {
        assertEquals(100, displayInTilesPromos.getMobileheight());
    }

    @Test
    void setId() {
        displayInTilesPromos.setId(2L);
        assertEquals(2L, displayInTilesPromos.getId());
    }

    @Test
    void setDisplayPromoTitle() {
        displayInTilesPromos.setDisplayPromoTitle("displayPromoTitle");
        assertEquals("displayPromoTitle", displayInTilesPromos.getDisplayPromoTitle());
    }

    @Test
    void setStartingDate() {
        Date date=new Date();
        displayInTilesPromos.setStartingDate(date);
        assertEquals(date, displayInTilesPromos.getStartingDate());
    }

    @Test
    void setEndingDate() {
        Date date=new Date();
        displayInTilesPromos.setEndingDate(date);
        assertEquals(date, displayInTilesPromos.getEndingDate());
    }

    @Test
    void setLink() {
        displayInTilesPromos.setLink("link1");
        assertEquals("link1", displayInTilesPromos.getLink());
    }

    @Test
    void setDesktopImage() {
        byte[] bytes=new byte[1];
        displayInTilesPromos.setDesktopImage(bytes);
        assertEquals(bytes, displayInTilesPromos.getDesktopImage());
    }

    @Test
    void setDesktopwidth() {
        displayInTilesPromos.setDesktopwidth(1090);
        assertEquals(1090, displayInTilesPromos.getDesktopwidth());
    }

    @Test
    void setDesktopheight() {
        displayInTilesPromos.setDesktopheight(910);
        assertEquals(910, displayInTilesPromos.getDesktopheight());
    }

    @Test
    void setStatus() {
        displayInTilesPromos.setStatus(false);
        assertEquals(false, displayInTilesPromos.isStatus());
    }

    @Test
    void setMobileImage() {
        byte[] bytes=new byte[1];
        displayInTilesPromos.setMobileImage(bytes);
        assertEquals(bytes, displayInTilesPromos.getMobileImage());
    }

    @Test
    void setMobilewidth() {
        displayInTilesPromos.setMobilewidth(101);
        assertEquals(101, displayInTilesPromos.getMobilewidth());
    }

    @Test
    void setMobileheight() {
        displayInTilesPromos.setMobileheight(101);
        assertEquals(101, displayInTilesPromos.getMobileheight());
    }
}