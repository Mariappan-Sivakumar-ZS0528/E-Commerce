package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDtoTest {

    CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto = new CustomerDto(1L, "name", "email", "number");
    }

    @Test
    void getId() {
        assertEquals(1L, customerDto.getId());
    }

    @Test
    void getName() {
        assertEquals("name", customerDto.getName());
    }

    @Test
    void getEmail() {
        assertEquals("email", customerDto.getEmail());
    }

    @Test
    void getNumber() {
        assertEquals("number", customerDto.getNumber());
    }

    @Test
    void setId() {
        customerDto.setId(2L);
        assertEquals(2L, customerDto.getId());
    }

    @Test
    void setName() {
        customerDto.setName("name1");
        assertEquals("name1", customerDto.getName());
    }

    @Test
    void setEmail() {
        customerDto.setEmail("email1");
        assertEquals("email1", customerDto.getEmail());
    }

    @Test
    void setNumber() {
        customerDto.setNumber("number1");
        assertEquals("number1", customerDto.getNumber());
    }
}