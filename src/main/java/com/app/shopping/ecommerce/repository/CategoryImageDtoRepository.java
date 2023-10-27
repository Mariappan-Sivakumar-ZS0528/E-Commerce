package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.payload.CategoryImageDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryImageDtoRepository extends JpaRepository<CategoryImageDto, Long> {
    CategoryImageDto findByMobileImageName(String fileName);
    CategoryImageDto findByDesktopImageName(String fileName);
}
