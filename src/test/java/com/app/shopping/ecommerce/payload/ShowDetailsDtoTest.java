package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowDetailsDtoTest {
    ShowDetailsDto showDetailsDto;
    @BeforeEach
    void setUp() {
        showDetailsDto = new ShowDetailsDto(1L,"name","address","email");
    }

    @Test
    void getId() {
        assertEquals(1L,showDetailsDto.getId());
    }

    @Test
    void getName() {
        assertEquals("name",showDetailsDto.getName());
    }

    @Test
    void getAddress() {
        assertEquals("address",showDetailsDto.getAddress());
    }

    @Test
    void getEmail() {
        assertEquals("email",showDetailsDto.getEmail());
    }

    @Test
    void setId() {
        showDetailsDto.setId(2L);
        assertEquals(2L,showDetailsDto.getId());
    }

    @Test
    void setName() {
        showDetailsDto.setName("name1");
        assertEquals("name1",showDetailsDto.getName());
    }

    @Test
    void setAddress() {
        showDetailsDto.setAddress("address1");
        assertEquals("address1",showDetailsDto.getAddress());
    }

    @Test
    void setEmail() {
        showDetailsDto.setEmail("email1");
        assertEquals("email1",showDetailsDto.getEmail());
    }
}