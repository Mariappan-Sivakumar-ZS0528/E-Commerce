package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FaqDtoTest {
    FaqDto faqDto;

    @BeforeEach
    void setUp() {
        faqDto = new FaqDto();
        faqDto = new FaqDto(1L, "question", "answer");
    }

    @Test
    void getId() {
        assertEquals(1L, faqDto.getId());
    }

    @Test
    void getQuestion() {
        assertEquals("question", faqDto.getQuestion());
    }

    @Test
    void getAnswer() {
        assertEquals("answer", faqDto.getAnswer());
    }

    @Test
    void setId() {
        faqDto.setId(2L);
        assertEquals(2L, faqDto.getId());
    }

    @Test
    void setQuestion() {
        faqDto.setQuestion("question1");
        assertEquals("question1", faqDto.getQuestion());
    }

    @Test
    void setAnswer() {
        faqDto.setAnswer("answer1");
        assertEquals("answer1", faqDto.getAnswer());
    }
}