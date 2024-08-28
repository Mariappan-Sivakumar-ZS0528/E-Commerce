package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;
    byte[] bytes=new byte[1];
    byte[] bytes2=new byte[1];
    List<Product> products=new ArrayList<>();
    List<SubCategory> subCategories=new ArrayList<>();

    @BeforeEach
    void setUp() {
        category=new Category(1L, "name", "description", "mobileImageName", "desktopImageName", "thumbnailImageName", "mobileImageType", "desktopImageType", "thumbnailImageType", bytes, bytes, bytes, subCategories, products);
    }

    @Test
    void getId() {
        assertEquals(1L, category.getId());
    }

    @Test
    void getName() {
        assertEquals("name", category.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description", category.getDescription());
    }

    @Test
    void getMobileImageName() {
        assertEquals("mobileImageName", category.getMobileImageName());
    }

    @Test
    void getDesktopImageName() {
        assertEquals("desktopImageName", category.getDesktopImageName());
    }

    @Test
    void getThumbnailImageName() {
        assertEquals("thumbnailImageName", category.getThumbnailImageName());
    }

    @Test
    void getMobileImageType() {
        assertEquals("mobileImageType", category.getMobileImageType());
    }

    @Test
    void getDesktopImageType() {
        assertEquals("desktopImageType", category.getDesktopImageType());
    }

    @Test
    void getThumbnailImageType() {
        assertEquals("thumbnailImageType", category.getThumbnailImageType());
    }

    @Test
    void getMobileImageData() {
        assertEquals(bytes, category.getMobileImageData());
    }

    @Test
    void getDesktopImageData() {
        assertEquals(bytes, category.getDesktopImageData());
    }

    @Test
    void getThumbnailImageData() {
        assertEquals(bytes, category.getThumbnailImageData());
    }

    @Test
    void getSubCategories() {
        assertEquals(subCategories, category.getSubCategories());
    }

    @Test
    void getProducts() {
        assertEquals(products, category.getProducts());
    }

    @Test
    void setId() {
        category.setId(2L);
        assertEquals(2L, category.getId());
    }

    @Test
    void setName() {
        category.setName("name2");
        assertEquals("name2", category.getName());
    }

    @Test
    void setDescription() {
        category.setDescription("description2");
        assertEquals("description2", category.getDescription());
    }

    @Test
    void setMobileImageName() {
        category.setMobileImageName("mobileImageName2");
        assertEquals("mobileImageName2", category.getMobileImageName());
    }

    @Test
    void setDesktopImageName() {
        category.setDesktopImageName("desktopImageName2");
        assertEquals("desktopImageName2", category.getDesktopImageName());
    }

    @Test
    void setThumbnailImageName() {
        category.setThumbnailImageName("thumbnailImageName2");
        assertEquals("thumbnailImageName2", category.getThumbnailImageName());
    }

    @Test
    void setMobileImageType() {
        category.setMobileImageType("mobileImageType2");
        assertEquals("mobileImageType2", category.getMobileImageType());
    }

    @Test
    void setDesktopImageType() {
        category.setDesktopImageType("desktopImageType2");
        assertEquals("desktopImageType2", category.getDesktopImageType());
    }

    @Test
    void setThumbnailImageType() {
        category.setThumbnailImageType("thumbnailImageType2");
        assertEquals("thumbnailImageType2", category.getThumbnailImageType());
    }

    @Test
    void setMobileImageData() {
        category.setMobileImageData(bytes2);
        assertEquals(bytes2 , category.getMobileImageData());
    }

    @Test
    void setDesktopImageData() {
        category.setDesktopImageData(bytes2);
        assertEquals(bytes2 , category.getDesktopImageData());
    }

    @Test
    void setThumbnailImageData() {
        category.setThumbnailImageData(bytes2);
        assertEquals(bytes2 , category.getThumbnailImageData());
    }

    @Test
    void setSubCategories() {
        List<SubCategory> subCategories=new ArrayList<>();
        category.setSubCategories(subCategories);
        assertEquals(subCategories, category.getSubCategories());
    }

    @Test
    void setProducts() {
        List<Product> products=new ArrayList<>();
        category.setProducts(products);
        assertEquals(products, category.getProducts());
    }
}