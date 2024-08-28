package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageDataTest {
    ImageData imageData;
    byte[] bytes=new byte[1];

    @BeforeEach
    void setUp() {
        imageData = new ImageData(1L, "name", "type", bytes);
    }

    @Test
    void getId() {
        assertEquals(1L, imageData.getId());
    }

    @Test
    void getName() {
        assertEquals("name", imageData.getName());
    }

    @Test
    void getType() {
        assertEquals("type", imageData.getType());
    }

    @Test
    void getImageData() {
        assertEquals(bytes, imageData.getImageData());
    }

    @Test
    void setId() {
        imageData.setId(2L);
        assertEquals(2L, imageData.getId());
    }

    @Test
    void setName() {
        imageData.setName("name1");
        assertEquals("name1", imageData.getName());
    }

    @Test
    void setType() {
        imageData.setType("type1");
        assertEquals("type1", imageData.getType());
    }

    @Test
    void setImageData() {
        byte[] bytes1=new byte[2];
        imageData.setImageData(bytes1);
        assertEquals(bytes1, imageData.getImageData());
    }

    @Test
    void testEquals() {
        ImageData imageData1 = new ImageData(1L, "name", "type", bytes);
        assertTrue(imageData.equals(imageData1));
    }

    @Test
    void testEquals2() {
        ImageData imageData1 = new ImageData(2L, "name", "type", bytes);
        assertFalse(imageData.equals(imageData1));
    }

    @Test
    void canEqual() {
        ImageData imageData1 = new ImageData(1L, "name", "type", bytes);
        assertTrue(imageData.canEqual(imageData1));
    }

    @Test
    void testHashCode() {
        ImageData imageData1 = new ImageData(1L, "name", "type", bytes);
        assertEquals(imageData.hashCode(), imageData1.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(imageData.toString());
    }

    @Test
    void builder() {
        assertNotNull(ImageData.builder().build());
    }
}