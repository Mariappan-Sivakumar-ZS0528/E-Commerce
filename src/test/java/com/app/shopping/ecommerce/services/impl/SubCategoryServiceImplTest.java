package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.entity.SubCategory;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.SubCategoryDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.repository.SubCategoryRepository;
import com.app.shopping.ecommerce.services.SubCategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SubCategoryServiceImplTest {
    @Autowired
    private SubCategoryService subCategoryService;
    @MockBean
    private SubCategoryRepository subCategoryRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    SubCategory subCategory;
    Category category;

    @BeforeEach
    void setUp() {
        subCategory=new SubCategory();
        subCategory.setId(1L);
        subCategory.setName("Milk");
        subCategory.setDescription("Diary Description");
        category=new Category();
        category.setId(1L);
        category.setName("Diary");
        subCategory.setCategory(category);
        Mockito.when(subCategoryRepository.save(Mockito.any(SubCategory.class))).thenReturn(subCategory);
        Mockito.when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));
        Mockito.when(subCategoryRepository.findByCategoryId(1L)).thenReturn(List.of(subCategory));
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(subCategoryRepository.findByCategoryId(1L)).thenReturn(List.of(subCategory));
    }

    @Test
    void createSubCategory() {
        SubCategoryDto subCategoryDto=new SubCategoryDto();
        subCategoryDto.setName("Milk");
        subCategoryDto.setDescription("Diary Description");
        SubCategoryDto subCategoryDto1=subCategoryService.createSubCategory(1L,subCategoryDto);
        Assertions.assertEquals("Milk",subCategoryDto1.getName());
        Assertions.assertEquals("Diary Description",subCategoryDto1.getDescription());
        Assertions.assertEquals("Diary",subCategoryDto1.getCategoryName());
    }
    @Test
    void createSubCategory1() {
        Assertions.assertThrows(ResourceNotFoundException.class,()->subCategoryService.createSubCategory(5L,new SubCategoryDto()));
    }

    @Test
    void updateSubCategory() {
        SubCategoryDto subCategoryDto=new SubCategoryDto();
        subCategoryDto.setName("Milk");
        subCategoryDto.setDescription("Milk Description");
        SubCategoryDto subCategoryDto1=subCategoryService.updateSubCategory(1L,subCategoryDto,1L);
        Assertions.assertEquals("Milk",subCategoryDto1.getName());
        Assertions.assertEquals("Milk Description",subCategoryDto1.getDescription());
        Assertions.assertEquals("Diary",subCategoryDto1.getCategoryName());
    }
    @Test
    void updateSubCategory1() {
        Assertions.assertThrows(ResourceNotFoundException.class,()->subCategoryService.updateSubCategory(5L,new SubCategoryDto(),1L));
        Assertions.assertThrows(ResourceNotFoundException.class,()->subCategoryService.updateSubCategory(1L,new SubCategoryDto(),5L));
    }

    @Test
    void getSubCategoryById() {
        SubCategoryDto subCategoryDto=subCategoryService.getSubCategoryById(1L,1L);
        Assertions.assertEquals("Milk",subCategoryDto.getName());
        Assertions.assertEquals("Diary Description",subCategoryDto.getDescription());
        Assertions.assertEquals("Diary",subCategoryDto.getCategoryName());
    }

    @Test
    void getSubCategoryById1() {
        Assertions.assertThrows(ResourceNotFoundException.class,()->subCategoryService.getSubCategoryById(5L,1L));
        Assertions.assertThrows(ResourceNotFoundException.class,()->subCategoryService.getSubCategoryById(1L,5L));
    }

    @Test
    void deleteSubCategory() {
        Assertions.assertDoesNotThrow(()->subCategoryService.deleteSubCategory(1L,1L));
    }
    @Test
    void deleteSubCategory1() {
        Assertions.assertThrows(ResourceNotFoundException.class,()->subCategoryService.deleteSubCategory(5L,1L));
    }

    @Test
    void getSubCategoriesByCategoryId() {
        List<SubCategoryDto> subCategoryDtos=subCategoryService.getSubCategoriesByCategoryId(1L);
        Assertions.assertEquals("Milk",subCategoryDtos.get(0).getName());
        Assertions.assertEquals("Diary Description",subCategoryDtos.get(0).getDescription());
        Assertions.assertEquals("Diary",subCategoryDtos.get(0).getCategoryName());
    }
    @Test
    void getSubCategoriesByCategoryId1() {
        Assertions.assertThrows(ResourceNotFoundException.class,()->subCategoryService.getSubCategoriesByCategoryId(5L));
    }
}