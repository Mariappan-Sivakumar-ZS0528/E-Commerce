package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.SubCategoryDto;
import com.app.shopping.ecommerce.services.SubCategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SubCategoryControllerTest {
    @Mock
    private SubCategoryService subCategoryService;
    @InjectMocks
    private SubCategoryController subCategoryController;
    @Test
    void createSubCategory() {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        when(subCategoryService.createSubCategory(1L, subCategoryDto)).thenReturn(subCategoryDto);
        assertEquals(subCategoryDto, subCategoryController.createSubCategory(1L, subCategoryDto).getBody());
    }

    @Test
    void getSubCategoriesByCategoryId() {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        when(subCategoryService.getSubCategoriesByCategoryId(1L)).thenReturn(List.of(subCategoryDto));
        assertEquals(List.of(subCategoryDto), subCategoryController.getSubCategoriesByCategoryId(1L).getBody());
    }

    @Test
    void getSubCategoryById() {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        when(subCategoryService.getSubCategoryById(1L,1L)).thenReturn(subCategoryDto);
        assertEquals(subCategoryDto, subCategoryController.getSubCategoryById(1L,1L).getBody());
    }

    @Test
    void updateSubCategory() {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        when(subCategoryService.updateSubCategory(1L, subCategoryDto,1L)).thenReturn(subCategoryDto);
        assertEquals(subCategoryDto, subCategoryController.updateSubCategory(1L,1L, subCategoryDto).getBody());
    }

    @Test
    void deleteSubCategory() {
        when(subCategoryService.deleteSubCategory(1L,1L)).thenReturn("SubCategory deleted successfully");
        assertEquals("SubCategory deleted successfully", subCategoryController.deleteSubCategory(1L,1L).getBody());
    }
}