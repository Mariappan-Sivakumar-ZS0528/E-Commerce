package com.app.shopping.ecommerce.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    ResourceNotFoundException resourceNotFoundException;

    @BeforeEach
    void setUp() {
        resourceNotFoundException = new ResourceNotFoundException("resourceName", "fieldName", "1");
    }

    @Test
    void getResourceName() {
        assertEquals("resourceName", resourceNotFoundException.getResourceName());
    }

    @Test
    void getFieldName() {
        assertEquals("fieldName", resourceNotFoundException.getFieldName());
    }
    @Test
    void getFieldValueString() {
        assertEquals("1", resourceNotFoundException.getFieldValueString());
    }

    @Test
    void getFieldValue() {
        resourceNotFoundException = new ResourceNotFoundException("resourceName", "fieldName", 1L);
        assertEquals(1L, resourceNotFoundException.getFieldValue());
    }


}