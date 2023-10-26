package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.entity.ImageData;
import com.app.shopping.ecommerce.entity.ImageData2;
import com.app.shopping.ecommerce.repository.StorageRepository;
import com.app.shopping.ecommerce.repository.StorageRepository2;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService2
{

    private StorageRepository2 storageRepository;

    public StorageService2(StorageRepository2 storageRepository) {
        this.storageRepository = storageRepository;
    }

    public String uploadImage(MultipartFile mobileImage,MultipartFile desktopImage) throws IOException {
//        ImageData2 imageData2 =storageRepository.save(ImageData2.builder().
//                mobileImageName(file.getOriginalFilename()).imageType(file.getContentType()).
//                imageData2(ImageUtils.compressImage(file.getBytes())).build());
//        ImageData2 mobileImageData =storageRepository.save(ImageData2.builder().
//                mobileImageName(mobileImage.getOriginalFilename()).imageType(mobileImage.getContentType()).
//                mobileImageData(ImageUtils.compressImage(mobileImage.getBytes())).build());
//        ImageData2 desktopImageData =storageRepository.save(ImageData2.builder().
//                desktopImageName(desktopImage.getOriginalFilename()).imageType(desktopImage.getContentType()).
//                desktopImageData(ImageUtils.compressImage(desktopImage.getBytes())).build());

        ImageData2 imageData2=storageRepository.save(ImageData2.builder().mobileImageName(mobileImage.getOriginalFilename()).
                              imageType(mobileImage.getContentType()).mobileImageData(ImageUtils.compressImage(mobileImage.getBytes())).
                              desktopImageName(desktopImage.getOriginalFilename()).imageType(desktopImage.getContentType()).
                              desktopImageData(ImageUtils.compressImage(desktopImage.getBytes())).build());
        if (imageData2!=null)
        {
            return "file uploaded successfully: "+mobileImage.getOriginalFilename()+" "+desktopImage.getOriginalFilename();
        }
        return null;
    }
    public byte[] downloadDesktopImage(String desktopImageName)
    {
        ImageData2 imageData=storageRepository.findByDesktopImageName(desktopImageName).orElse(null);
        if (imageData!=null)
        {
            return ImageUtils.decompressImage(imageData.getDesktopImageData());
        }
        return null;
    }
    public byte[] downloadMobileImage(String mobileImageName)
    {
        ImageData2 imageData=storageRepository.findByMobileImageName(mobileImageName).orElse(null);
        if (imageData!=null)
        {
            return ImageUtils.decompressImage(imageData.getMobileImageData());
        }
        return null;
    }
    
}
