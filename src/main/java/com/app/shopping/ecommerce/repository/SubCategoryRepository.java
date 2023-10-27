package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> getSubCategoriesByCategoryId(Long categoryId);
}
