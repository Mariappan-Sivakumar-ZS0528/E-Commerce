package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailsTest {
    ErrorDetails errorDetails;
    Date date = new Date();

    @BeforeEach
    void setUp() {
        errorDetails = new ErrorDetails(date, "message", "details");
    }

    @Test
    void getTimestamp() {
        assertEquals(date, errorDetails.getTimestamp());
    }

    @Test
    void getMessage() {
        assertEquals("message", errorDetails.getMessage());
    }

    @Test
    void getDetails() {
        assertEquals("details", errorDetails.getDetails());
    }
}