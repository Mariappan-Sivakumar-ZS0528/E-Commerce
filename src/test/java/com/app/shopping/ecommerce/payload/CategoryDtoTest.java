package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDtoTest {
    CategoryDto categoryDto;
    List<SubCategoryDto> subCategories;
    @BeforeEach
    void setUp() {
        categoryDto=new CategoryDto(1L, "Chicken", "Chicken Description", subCategories, "mobile", "desktop", "thumbnail");
    }
    @Test
    void getId() {
        assertEquals(1L, categoryDto.getId());
    }

    @Test
    void getName() {
        assertEquals("Chicken", categoryDto.getName());
    }

    @Test
    void getDescription() {
        assertEquals("Chicken Description", categoryDto.getDescription());
    }

    @Test
    void getSubCategories() {
        assertEquals(subCategories, categoryDto.getSubCategories());
    }

    @Test
    void getMobileImageName() {
        assertEquals("mobile", categoryDto.getMobileImageName());
    }

    @Test
    void getDesktopImageName() {
        assertEquals("desktop", categoryDto.getDesktopImageName());
    }

    @Test
    void getThumbnailImageName() {
        assertEquals("thumbnail", categoryDto.getThumbnailImageName());
    }

    @Test
    void setId() {
        categoryDto.setId(2L);
        assertEquals(2L, categoryDto.getId());
    }

    @Test
    void setName() {
        categoryDto.setName("Chicken1");
        assertEquals("Chicken1", categoryDto.getName());
    }

    @Test
    void setDescription() {
        categoryDto.setDescription("Chicken Description1");
        assertEquals("Chicken Description1", categoryDto.getDescription());
    }

    @Test
    void setSubCategories() {
        categoryDto.setSubCategories(subCategories);
        assertEquals(subCategories, categoryDto.getSubCategories());
    }

    @Test
    void setMobileImageName() {
        categoryDto.setMobileImageName("mobile1");
        assertEquals("mobile1", categoryDto.getMobileImageName());
    }

    @Test
    void setDesktopImageName() {
        categoryDto.setDesktopImageName("desktop1");
        assertEquals("desktop1", categoryDto.getDesktopImageName());
    }

    @Test
    void setThumbnailImageName() {
        categoryDto.setThumbnailImageName("thumbnail1");
        assertEquals("thumbnail1", categoryDto.getThumbnailImageName());
    }
}