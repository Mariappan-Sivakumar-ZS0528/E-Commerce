package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BannerTest {
    Banner banner;
    byte[] bytes=new byte[1];

    @BeforeEach
    void setUp() {
        banner = new Banner(1L, "mobileImageName", "desktopImageName", "imageType", "mobileLink", "desktopLink", "title", bytes, bytes);
    }

    @Test
    void getId() {
        assertEquals(1L, banner.getId());
    }

    @Test
    void getMobileImageName() {
        assertEquals("mobileImageName", banner.getMobileImageName());
    }

    @Test
    void getDesktopImageName() {
        assertEquals("desktopImageName", banner.getDesktopImageName());
    }

    @Test
    void getImageType() {
        assertEquals("imageType", banner.getImageType());
    }

    @Test
    void getMobileLink() {
        assertEquals("mobileLink", banner.getMobileLink());
    }

    @Test
    void getDesktopLink() {
        assertEquals("desktopLink", banner.getDesktopLink());
    }

    @Test
    void getTitle() {
        assertEquals("title", banner.getTitle());
    }

    @Test
    void getMobileImageData() {
        assertEquals(bytes, banner.getMobileImageData());
    }

    @Test
    void getDesktopImageData() {
        assertEquals(bytes, banner.getDesktopImageData());
    }

    @Test
    void setId() {
        banner.setId(2L);
        assertEquals(2L, banner.getId());
    }

    @Test
    void setMobileImageName() {
        banner.setMobileImageName("mobile Image Name");
        assertEquals("mobile Image Name", banner.getMobileImageName());
    }

    @Test
    void setDesktopImageName() {
        banner.setDesktopImageName("desktop Image Name");
        assertEquals("desktop Image Name", banner.getDesktopImageName());
    }

    @Test
    void setImageType() {
        banner.setImageType("image type");
        assertEquals("image type", banner.getImageType());
    }

    @Test
    void setMobileLink() {
        banner.setMobileLink("mobile link");
        assertEquals("mobile link", banner.getMobileLink());
    }

    @Test
    void setDesktopLink() {
        banner.setDesktopLink("desktop link");
        assertEquals("desktop link", banner.getDesktopLink());
    }

    @Test
    void setTitle() {
        banner.setTitle("title name");
        assertEquals("title name", banner.getTitle());
    }

    @Test
    void setMobileImageData() {
        byte[] bytes=new byte[1];
        banner.setMobileImageData(bytes);
        assertEquals(bytes, banner.getMobileImageData());
    }

    @Test
    void setDesktopImageData() {
        byte[] bytes=new byte[1];
        banner.setDesktopImageData(bytes);
        assertEquals(bytes, banner.getDesktopImageData());
    }

    @Test
    void testEquals() {
        assertTrue(banner.equals(banner));
    }

    @Test
    void testNotEquals(){
        Banner banner1=new Banner();
        assertFalse(banner.equals(banner1));
    }

    @Test
    void canEqual() {
        assertTrue(banner.canEqual(banner));
    }

    @Test
    void testHashCode() {
        Banner banner1=new Banner();
        assertNotEquals(banner.hashCode(), banner1.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(banner.toString());
    }

    @Test
    void builder() {
        assertNotNull(Banner.builder().build());
    }
}