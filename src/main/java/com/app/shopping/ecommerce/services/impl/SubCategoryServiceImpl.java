package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.entity.SubCategory;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.payload.SubCategoryDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.repository.SubCategoryRepository;
import com.app.shopping.ecommerce.services.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private ModelMapper modelMapper;
    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SubCategoryDto createSubCategory(long categoryId, SubCategoryDto subCategoryDto) {
        SubCategory subCategory=modelMapper.map(subCategoryDto, SubCategory.class);
        Category category= categoryRepository.findById(categoryId).orElseThrow();
        subCategory.setCategory(category);
        SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
        return modelMapper.map(savedSubCategory, SubCategoryDto.class);
    }

    @Override
    public SubCategoryDto updateSubCategory(long categoryId, SubCategoryDto subCategoryDto, Long id) {
        SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow();
        Category category=categoryRepository.findById(categoryId).orElseThrow();
        if (!category.getId().equals(subCategory.getCategory().getId())){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"SubCategory is not belong to this category");
        }
        subCategory.setName(subCategoryDto.getName());
        subCategory.setDescription(subCategoryDto.getDescription());
        SubCategory updatedSubCategory=subCategoryRepository.save(subCategory);
        return modelMapper.map(updatedSubCategory, SubCategoryDto.class);
    }

    @Override
    public SubCategoryDto getSubCategoryById(long categoryId, Long subCategoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow();
        SubCategory subCategory=subCategoryRepository.findById(subCategoryId).orElseThrow();
        if (!category.getId().equals(subCategory.getCategory().getId())){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"SubCategory is not belong to this category");
        }
        return modelMapper.map(subCategory, SubCategoryDto.class);
    }

    @Override
    public void deleteSubCategory(long categoryId, Long subCategoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow();
        SubCategory subCategory=subCategoryRepository.findById(subCategoryId).orElseThrow();
        if (!category.getId().equals(subCategory.getCategory().getId())){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"SubCategory is not belong to this category");
        }
        subCategoryRepository.delete(subCategory);
    }

    @Override
    public List<SubCategoryDto> getSubCategoriesByCategoryId(Long categoryId) {
        List<SubCategory> subCategories=subCategoryRepository.getSubCategoriesByCategoryId(categoryId);
        return subCategories.stream().map(subCategory -> modelMapper.map(subCategory, SubCategoryDto.class)).toList();
    }
}