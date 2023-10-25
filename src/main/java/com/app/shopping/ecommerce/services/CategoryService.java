package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Long id);

}
