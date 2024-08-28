package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LegalDtoTest {
    LegalDto legalDto;
    @BeforeEach
    void setUp() {
        legalDto = new LegalDto(1L,"title","content");
    }

    @Test
    void getId() {
        assertEquals(1L,legalDto.getId());
    }

    @Test
    void getTitle() {
        assertEquals("title",legalDto.getTitle());
    }

    @Test
    void getContent() {
        assertEquals("content",legalDto.getContent());
    }

    @Test
    void setId() {
        legalDto.setId(2L);
        assertEquals(2L,legalDto.getId());
    }

    @Test
    void setTitle() {
        legalDto.setTitle("title");
        assertEquals("title",legalDto.getTitle());
    }

    @Test
    void setContent() {
        legalDto.setContent("content");
        assertEquals("content",legalDto.getContent());
    }
}