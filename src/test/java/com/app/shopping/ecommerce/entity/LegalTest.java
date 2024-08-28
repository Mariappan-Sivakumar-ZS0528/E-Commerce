package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LegalTest {
    Legal legal;

    @BeforeEach
    void setUp() {
        legal = new Legal(1L, "title", "content");
    }

    @Test
    void getId() {
        assertEquals(1L, legal.getId());
    }

    @Test
    void getTitle() {
        assertEquals("title", legal.getTitle());
    }

    @Test
    void getContent() {
        assertEquals("content", legal.getContent());
    }

    @Test
    void setId() {
        legal.setId(2L);
        assertEquals(2L, legal.getId());
    }

    @Test
    void setTitle() {
        legal.setTitle("title1");
        assertEquals("title1", legal.getTitle());
    }

    @Test
    void setContent() {
        legal.setContent("content1");
        assertEquals("content1", legal.getContent());
    }
}