package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.SubCategoryDto;

import java.util.List;

public interface SubCategoryService {
    SubCategoryDto createSubCategory(long categoryId,SubCategoryDto subCategoryDto);
    SubCategoryDto updateSubCategory(long categoryId,SubCategoryDto subCategoryDto,Long id);
    SubCategoryDto getSubCategoryById(long categoryId,Long subCategoryId);
    String deleteSubCategory(long categoryId,Long subCategoryId);
    List<SubCategoryDto> getSubCategoriesByCategoryId(Long categoryId);
}
