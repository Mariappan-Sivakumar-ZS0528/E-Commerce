package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Banner;
import com.app.shopping.ecommerce.payload.BannerLinkDto;
import com.app.shopping.ecommerce.repository.BannerRepository;
import com.app.shopping.ecommerce.services.BannerService;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class BannerServiceimpl implements BannerService {

    private BannerRepository storageRepository;
    private ModelMapper modelMapper;

    public BannerServiceimpl(BannerRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    public String uploadImage(MultipartFile mobileImage, MultipartFile desktopImage) throws IOException {
//        ImageData2 imageData2 =storageRepository.save(ImageData2.builder().
//                mobileImageName(file.getOriginalFilename()).imageType(file.getContentType()).
//                imageData2(ImageUtils.compressImage(file.getBytes())).build());
//        ImageData2 mobileImageData =storageRepository.save(ImageData2.builder().
//                mobileImageName(mobileImage.getOriginalFilename()).imageType(mobileImage.getContentType()).
//                mobileImageData(ImageUtils.compressImage(mobileImage.getBytes())).build());
//        ImageData2 desktopImageData =storageRepository.save(ImageData2.builder().
//                desktopImageName(desktopImage.getOriginalFilename()).imageType(desktopImage.getContentType()).
//                desktopImageData(ImageUtils.compressImage(desktopImage.getBytes())).build());

        Banner banner = storageRepository.save(Banner.builder().mobileImageName(mobileImage.getOriginalFilename()).
                imageType(mobileImage.getContentType()).mobileImageData(ImageUtils.compressImage(mobileImage.getBytes())).
                desktopImageName(desktopImage.getOriginalFilename()).imageType(desktopImage.getContentType()).
                desktopImageData(ImageUtils.compressImage(desktopImage.getBytes())).build());
        if (banner != null) {
            return "file uploaded successfully: " + mobileImage.getOriginalFilename() + " " + desktopImage.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadDesktopImage(Long id,String desktopImageName) {
        Banner banner = storageRepository.findById(id).orElse(null);
        if (banner != null&& Objects.equals(banner.getDesktopImageName(), desktopImageName)) {
            return ImageUtils.decompressImage(banner.getDesktopImageData());
        }
//        Banner imageData = storageRepository.findByDesktopImageName(desktopImageName).orElse(null);
//        if (imageData != null) {
//            return ImageUtils.decompressImage(imageData.getDesktopImageData());
//        }
        return null;
    }

    public byte[] downloadMobileImage(Long id,String mobileImageName) {
        Banner banner = storageRepository.findById(id).orElse(null);
        if (banner != null&& Objects.equals(banner.getMobileImageName(), mobileImageName))
        {
            return ImageUtils.decompressImage(banner.getMobileImageData());
        }
        return null;
    }

    @Override
    public BannerLinkDto uploadLink(BannerLinkDto bannerLinkDto)
    {
        Banner banner = storageRepository.save(modelMapper.map(bannerLinkDto, Banner.class));
        if (banner != null) {
            return modelMapper.map(banner, BannerLinkDto.class);
        }
        return null;
    }

    @Override
    public String updateBannerImages(Long id, MultipartFile mobileImage, MultipartFile desktopImage) throws IOException {
        Banner banner = storageRepository.findById(id).orElse(null);
        if (banner != null) {
            banner.setMobileImageName(mobileImage.getOriginalFilename());
            banner.setImageType(mobileImage.getContentType());
            banner.setMobileImageData(ImageUtils.compressImage(mobileImage.getBytes()));
            banner.setDesktopImageName(desktopImage.getOriginalFilename());
            banner.setImageType(desktopImage.getContentType());
            banner.setDesktopImageData(ImageUtils.compressImage(desktopImage.getBytes()));
            storageRepository.save(banner);
            return "mobile image uploaded successfully: " + mobileImage.getOriginalFilename() +"/n"+ " desktop image uploaded successfully: " + desktopImage.getOriginalFilename();
        }
        return null;
    }

}
