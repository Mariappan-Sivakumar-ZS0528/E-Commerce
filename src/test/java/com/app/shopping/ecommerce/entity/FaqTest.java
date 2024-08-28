package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FaqTest {
    Faq faq;
    @BeforeEach
    void setUp() {
        faq = new Faq(1L, "question", "answer");
    }

    @Test
    void getId() {
        assertEquals(1L, faq.getId());
    }

    @Test
    void getQuestion() {
        assertEquals("question", faq.getQuestion());
    }

    @Test
    void getAnswer() {
        assertEquals("answer", faq.getAnswer());
    }

    @Test
    void setId() {
        faq.setId(2L);
        assertEquals(2L, faq.getId());
    }

    @Test
    void setQuestion() {
        faq.setQuestion("question1");
        assertEquals("question1", faq.getQuestion());
    }

    @Test
    void setAnswer() {
        faq.setAnswer("answer1");
        assertEquals("answer1", faq.getAnswer());
    }
}