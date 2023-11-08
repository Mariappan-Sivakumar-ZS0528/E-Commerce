package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.payload.CategoryDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;
    @MockBean
    CategoryRepository categoryRepository;
    Category category;

    @BeforeEach
    void setUp() {
        category=new Category();
        category.setId(1L);
        category.setName("Diary");
        category.setDescription("Diary Description");
        category.setDesktopImageName("desktop.png");
        category.setMobileImageName("mobile.png");
        category.setThumbnailImageName("thumbnail.png");
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category));
    }

    @Test
    void addCategory() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("Diary");
        CategoryDto categoryDto1=categoryService.addCategory(categoryDto);
        assertEquals("Diary", categoryDto1.getName());
    }

    @Test
    void updateCategory() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("Chicken");
        CategoryDto categoryDto1=categoryService.updateCategory(categoryDto, 1L);
        assertEquals("Chicken", categoryDto1.getName());
    }

    @Test
    void getCategory() {
        CategoryDto categoryDto=categoryService.getCategory(1L);
        assertEquals("Diary", categoryDto.getName());
    }

    @Test
    void getAllCategories() {
        List<CategoryDto> categoryDtos=categoryService.getAllCategories();
        assertEquals("Diary", categoryDtos.get(0).getName());
    }

    @Test
    void deleteCategory() {
        assertDoesNotThrow(() -> categoryService.deleteCategory(1L));
    }

//    @Test
//    void updateCategoryImage() {
//    }
//
//    @Test
//    void downloadMobileImage() {
//    }
//
//    @Test
//    void downloadDesktopImage() {
//    }
//
//    @Test
//    void downloadThumbnailImage() {
//    }
}