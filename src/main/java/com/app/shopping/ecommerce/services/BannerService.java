package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.BannerLinkDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BannerService {
    String uploadImage(MultipartFile mobileImage, MultipartFile desktopImage) throws IOException;
     byte[] downloadDesktopImage(Long id,String desktopImageName);
     byte[] downloadMobileImage(Long id,String mobileImageName);

    BannerLinkDto uploadLink(BannerLinkDto bannerLinkDto);
     String updateBannerImages(Long id, MultipartFile mobileImage, MultipartFile desktopImage) throws IOException;
}
