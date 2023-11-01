package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByMobileImageName(String fileName);
    Optional<Category> findByDesktopImageName(String fileName);
    Optional<Category> findByThumbnailImageName(String fileName);
    Optional<Category> findByName(String name);
}
