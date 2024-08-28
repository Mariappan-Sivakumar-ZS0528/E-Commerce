package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.entity.SubCategory;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.SubCategoryDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.repository.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SubCategoryServiceImplTest {
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private SubCategoryRepository subCategoryRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private SubCategoryServiceImpl subCategoryService;
    SubCategoryDto subCategoryDto;
    SubCategory subCategory;
    Category category;
    @BeforeEach
    void setUp() {
        subCategoryDto = new SubCategoryDto();
        subCategoryDto.setName("test");
        subCategory = new SubCategory();
        subCategory.setName(subCategoryDto.getName());
        category = new Category();
        category.setId(1L);
        subCategory.setCategory(category);
    }

    @Test
    void createSubCategory() {
        when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);
        when(modelMapper.map(subCategoryDto, SubCategory.class)).thenReturn(subCategory);
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));
        when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);
        when(modelMapper.map(subCategory, SubCategoryDto.class)).thenReturn(subCategoryDto);
        assertEquals(subCategoryDto, subCategoryService.createSubCategory(1L, subCategoryDto));
    }

    @Test
    void updateSubCategory() {
        when(subCategoryRepository.findById(1L)).thenReturn(java.util.Optional.of(subCategory));
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));
        when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);
        when(modelMapper.map(subCategory, SubCategoryDto.class)).thenReturn(subCategoryDto);
        assertEquals(subCategoryDto, subCategoryService.updateSubCategory(1L, subCategoryDto, 1L));
    }
    @Test
    void updateSubCategoryfordifferentData(){
        when(subCategoryRepository.findById(1L)).thenReturn(java.util.Optional.of(subCategory));
        Category category1 = new Category();
        category1.setId(2L);
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category1));
        when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);
        when(modelMapper.map(subCategory, SubCategoryDto.class)).thenReturn(subCategoryDto);
        assertThrows(ECommerceApiException.class,()->subCategoryService.updateSubCategory(1L, subCategoryDto, 1L));
    }
    @Test
    void getSubCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));
        when(subCategoryRepository.findById(1L)).thenReturn(java.util.Optional.of(subCategory));
        when(modelMapper.map(subCategory, SubCategoryDto.class)).thenReturn(subCategoryDto);
        assertEquals(subCategoryDto, subCategoryService.getSubCategoryById(1L, 1L));
    }
    @Test
    void getSubCategoryByIdForDifferentData(){
        Category category1 = new Category();
        category1.setId(2L);
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category1));
        when(subCategoryRepository.findById(1L)).thenReturn(java.util.Optional.of(subCategory));
        when(modelMapper.map(subCategory, SubCategoryDto.class)).thenReturn(subCategoryDto);
        assertThrows(ECommerceApiException.class,()->subCategoryService.getSubCategoryById(1L, 1L));
    }

    @Test
    void deleteSubCategory() {
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));
        when(subCategoryRepository.findById(1L)).thenReturn(java.util.Optional.of(subCategory));
        assertEquals("SubCategory deleted successfully", subCategoryService.deleteSubCategory(1L, 1L));
    }
    @Test
    void deleteSubCategoryForFailure(){
        Category category1 = new Category();
        category1.setId(2L);
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category1));
        when(subCategoryRepository.findById(1L)).thenReturn(java.util.Optional.of(subCategory));
        assertThrows(ECommerceApiException.class,()->subCategoryService.deleteSubCategory(1L, 1L));
    }

    @Test
    void getSubCategoriesByCategoryId() {
     when(subCategoryRepository.findByCategoryId(1L)).thenReturn(List.of(subCategory));
     when(modelMapper.map(subCategory, SubCategoryDto.class)).thenReturn(subCategoryDto);
     assertEquals(List.of(subCategoryDto),subCategoryService.getSubCategoriesByCategoryId(1L));
    }
    @Test
    void getSubCategoriesByCategoryIdForFailure(){
        when(subCategoryRepository.findByCategoryId(1L)).thenReturn(new ArrayList<>());
        when(modelMapper.map(subCategory, SubCategoryDto.class)).thenReturn(subCategoryDto);
        assertThrows(ResourceNotFoundException.class,()->subCategoryService.getSubCategoriesByCategoryId(2L));
    }
}