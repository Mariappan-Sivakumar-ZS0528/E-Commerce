package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Long id);
    String updateCategoryImage(MultipartFile mobileImage, MultipartFile desktopImage,MultipartFile thumbnailImage, Long categoryId) throws IOException;
    byte[] downloadMobileImage(String mobileImageName);
    byte[] downloadDesktopImage(String desktopImageName);
    byte[] downloadThumbnailImage(String thumbnailImageName);

}
