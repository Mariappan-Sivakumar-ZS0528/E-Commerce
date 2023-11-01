package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> getSubCategoriesByCategoryId(Long categoryId);
    Optional<SubCategory> findByName(String name);
}
