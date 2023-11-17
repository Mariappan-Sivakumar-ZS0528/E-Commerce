package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
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
        categoryDto.setDescription("Diary Description");
        categoryDto.setDesktopImageName("desktop.png");
        categoryDto.setMobileImageName("mobile.png");
        categoryDto.setThumbnailImageName("thumbnail.png");
        CategoryDto categoryDto1=categoryService.addCategory(categoryDto);
        assertEquals("Diary", categoryDto1.getName());
        assertEquals("Diary Description", categoryDto1.getDescription());
        assertEquals("desktop.png", categoryDto1.getDesktopImageName());
        assertEquals("mobile.png", categoryDto1.getMobileImageName());
        assertEquals("thumbnail.png", categoryDto1.getThumbnailImageName());
    }

    @Test
    void updateCategory() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("Chicken");
        categoryDto.setDescription("Chicken Description");
        CategoryDto categoryDto1=categoryService.updateCategory(categoryDto, 1L);
        assertEquals("Chicken", categoryDto1.getName());
        assertEquals("Chicken Description", categoryDto1.getDescription());
    }
    @Test
    void updateCategory1() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.updateCategory(new CategoryDto(), 5L));
    }

    @Test
    void getCategory() {
        CategoryDto categoryDto=categoryService.getCategory(1L);
        assertEquals("Diary", categoryDto.getName());
        assertEquals("Diary Description", categoryDto.getDescription());
        assertEquals("desktop.png", categoryDto.getDesktopImageName());
        assertEquals("mobile.png", categoryDto.getMobileImageName());
        assertEquals("thumbnail.png", categoryDto.getThumbnailImageName());
    }
    @Test
    void getCategory1() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.getCategory(5L));
    }

    @Test
    void getAllCategories() {
        List<CategoryDto> categoryDtos=categoryService.getAllCategories();
        assertEquals("Diary", categoryDtos.get(0).getName());
        assertEquals("Diary Description", categoryDtos.get(0).getDescription());
        assertEquals("desktop.png", categoryDtos.get(0).getDesktopImageName());
        assertEquals("mobile.png", categoryDtos.get(0).getMobileImageName());
        assertEquals("thumbnail.png", categoryDtos.get(0).getThumbnailImageName());
    }

    @Test
    void deleteCategory() {
        assertDoesNotThrow(() -> categoryService.deleteCategory(1L));
    }
    @Test
    void deleteCategory1() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.deleteCategory(5L));
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