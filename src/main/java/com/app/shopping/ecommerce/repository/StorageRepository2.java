package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.ImageData;
import com.app.shopping.ecommerce.entity.ImageData2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository2 extends JpaRepository<ImageData2, Long>
{
    Optional<ImageData2> findByMobileImageName(String fileName);
    Optional<ImageData2> findByDesktopImageName(String fileName);
}
