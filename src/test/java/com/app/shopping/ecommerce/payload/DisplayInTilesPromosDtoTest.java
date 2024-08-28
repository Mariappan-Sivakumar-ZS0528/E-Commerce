package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DisplayInTilesPromosDtoTest {
    DisplayInTilesPromosDto displayInTilesPromosDto;
    Date date = new Date();

    @BeforeEach
    void setUp() {
        displayInTilesPromosDto = new DisplayInTilesPromosDto();
        displayInTilesPromosDto = new DisplayInTilesPromosDto(1L, "displayPromoTitle", date,date, "link");
    }

    @Test
    void getId() {
        assertEquals(1L, displayInTilesPromosDto.getId());
    }

    @Test
    void getDisplayPromoTitle() {
        assertEquals("displayPromoTitle", displayInTilesPromosDto.getDisplayPromoTitle());
    }

    @Test
    void getStartingDate() {
        assertEquals(date, displayInTilesPromosDto.getStartingDate());
    }

    @Test
    void getEndingDate() {
        assertEquals(date, displayInTilesPromosDto.getEndingDate());
    }

    @Test
    void getLink() {
        assertEquals("link", displayInTilesPromosDto.getLink());
    }

    @Test
    void setId() {
        displayInTilesPromosDto.setId(2L);
        assertEquals(2L, displayInTilesPromosDto.getId());
    }

    @Test
    void setDisplayPromoTitle() {
        displayInTilesPromosDto.setDisplayPromoTitle("displayPromoTitle1");
        assertEquals("displayPromoTitle1", displayInTilesPromosDto.getDisplayPromoTitle());
    }

    @Test
    void setStartingDate() {
        Date date = new Date();
        displayInTilesPromosDto.setStartingDate(date);
        assertEquals(date, displayInTilesPromosDto.getStartingDate());
    }

    @Test
    void setEndingDate() {
        Date date = new Date();
        displayInTilesPromosDto.setEndingDate(date);
        assertEquals(date, displayInTilesPromosDto.getEndingDate());
    }

    @Test
    void setLink() {
        displayInTilesPromosDto.setLink("link1");
        assertEquals("link1", displayInTilesPromosDto.getLink());
    }
}