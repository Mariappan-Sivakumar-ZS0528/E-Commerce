package com.app.shopping.ecommerce.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ECommerceApiExceptionTest {
    ECommerceApiException exception;

    @BeforeEach
    void setUp() {
        exception = new ECommerceApiException(HttpStatus.BAD_REQUEST, "message");
    }

    @Test
    void getStatus() {
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    void getMessage() {
        assertEquals("message", exception.getMessage());
    }
}