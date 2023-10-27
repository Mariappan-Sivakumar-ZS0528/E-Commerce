package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long>
{
    Optional<Banner> findByMobileImageName(String fileName);
    Optional<Banner> findByDesktopImageName(String fileName);
    Optional<Banner> findById(Long id);
}
