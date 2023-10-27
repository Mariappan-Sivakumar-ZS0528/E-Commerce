package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.payload.CategoryDto;
import com.app.shopping.ecommerce.payload.CategoryImageDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.services.CategoryService;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto, Category.class);
        Category updatedCategory=categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory=categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow();
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
    }



    @Override
    public String updateCategoryImage(MultipartFile mobileImage, MultipartFile desktopImage,MultipartFile thumbnailImage, Long categoryId) throws IOException {
        Category category=categoryRepository.findById(categoryId).orElseThrow();
        category.setMobileImageName(mobileImage.getOriginalFilename());
        category.setDesktopImageName(desktopImage.getOriginalFilename());
        category.setThumbnailImageName(thumbnailImage.getOriginalFilename());
        category.setMobileImageData(ImageUtils.compressImage(mobileImage.getBytes()));
        category.setDesktopImageData(ImageUtils.compressImage(desktopImage.getBytes()));
        category.setThumbnailImageData(ImageUtils.compressImage(thumbnailImage.getBytes()));
        categoryRepository.save(category);
        return "Category image updated successfully";
    }

    @Override
    public byte[] downloadMobileImage(String mobileImageName) {
        return new byte[0];
    }

    @Override
    public byte[] downloadDesktopImage(String desktopImageName) {
        return new byte[0];
    }
    @Override
    public byte[] downloadThumbnailImage(String thumbnailImageName) {
        return new byte[0];
    }
}
