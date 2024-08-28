package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SubCategoryTest {
    SubCategory subCategory;
    Category category = new Category();
    Set<Product> products = Set.of(new Product());
    @BeforeEach
    void setUp() {
        subCategory = new SubCategory(1L, "test","test",category,products);
    }

    @Test
    void getId() {
        assertEquals(1L, subCategory.getId());
    }

    @Test
    void getName() {
        assertEquals("test", subCategory.getName());
    }

    @Test
    void getDescription() {
        assertEquals("test", subCategory.getDescription());
    }

    @Test
    void getCategory() {
        assertEquals(category, subCategory.getCategory());
    }

    @Test
    void getProducts() {
        assertEquals(products, subCategory.getProducts());
    }

    @Test
    void setId() {
        subCategory.setId(2L);
        assertEquals(2L, subCategory.getId());
    }

    @Test
    void setName() {
        subCategory.setName("test1");
        assertEquals("test1", subCategory.getName());
    }

    @Test
    void setDescription() {
        subCategory.setDescription("test1");
        assertEquals("test1", subCategory.getDescription());
    }

    @Test
    void setCategory() {
        Category category1=new Category();
        subCategory.setCategory(category1);
        assertEquals(category1, subCategory.getCategory());
    }

    @Test
    void setProducts() {
        Set<Product> products1 = Set.of(new Product());
        subCategory.setProducts(products1);
        assertEquals(products1, subCategory.getProducts());
    }
}