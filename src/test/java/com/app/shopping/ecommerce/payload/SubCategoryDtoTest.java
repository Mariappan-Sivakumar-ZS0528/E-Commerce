package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubCategoryDtoTest {
    SubCategoryDto subCategoryDto;
    @BeforeEach
    void setUp() {
        subCategoryDto = new SubCategoryDto(1L,"name","description","categoryName");
    }

    @Test
    void getId() {
        assertEquals(1L,subCategoryDto.getId());
    }

    @Test
    void getName() {
        assertEquals("name",subCategoryDto.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description",subCategoryDto.getDescription());
    }

    @Test
    void getCategoryName() {
        assertEquals("categoryName",subCategoryDto.getCategoryName());
    }

    @Test
    void setId() {
        subCategoryDto.setId(2L);
        assertEquals(2L,subCategoryDto.getId());
    }

    @Test
    void setName() {
        subCategoryDto.setName("newName");
        assertEquals("newName",subCategoryDto.getName());
    }

    @Test
    void setDescription() {
        subCategoryDto.setDescription("newDescription");
        assertEquals("newDescription",subCategoryDto.getDescription());
    }

    @Test
    void setCategoryName() {
        subCategoryDto.setCategoryName("newCategoryName");
        assertEquals("newCategoryName",subCategoryDto.getCategoryName());
    }
}