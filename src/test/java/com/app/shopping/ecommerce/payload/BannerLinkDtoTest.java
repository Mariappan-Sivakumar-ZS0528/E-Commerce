package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BannerLinkDtoTest {
    BannerLinkDto bannerLinkDto;

    @BeforeEach
    void setUp() {
        bannerLinkDto = new BannerLinkDto(1L, "title", "desktopLink", "mobileLink");
    }

    @Test
    void getId() {
        assertEquals(1L, bannerLinkDto.getId());
    }

    @Test
    void getTitle() {
        assertEquals("title", bannerLinkDto.getTitle());
    }

    @Test
    void getDesktopLink() {
        assertEquals("desktopLink", bannerLinkDto.getDesktopLink());
    }

    @Test
    void getMobileLink() {
        assertEquals("mobileLink", bannerLinkDto.getMobileLink());
    }

    @Test
    void setId() {
        bannerLinkDto.setId(2L);
        assertEquals(2L, bannerLinkDto.getId());
    }

    @Test
    void setTitle() {
        bannerLinkDto.setTitle("title1");
        assertEquals("title1", bannerLinkDto.getTitle());
    }

    @Test
    void setDesktopLink() {
        bannerLinkDto.setDesktopLink("desktopLink1");
        assertEquals("desktopLink1", bannerLinkDto.getDesktopLink());
    }

    @Test
    void setMobileLink() {
        bannerLinkDto.setMobileLink("mobileLink1");
        assertEquals("mobileLink1", bannerLinkDto.getMobileLink());
    }
}