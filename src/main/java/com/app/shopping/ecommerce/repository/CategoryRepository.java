package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.payload.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    CategoryDto findByMobileImageName(String fileName);
    CategoryDto findByDesktopImageName(String fileName);
    CategoryDto findByThumbnailImageName(String fileName);
}
