package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.entity.ImageData;
import com.app.shopping.ecommerce.repository.StorageRepository;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService
{

    private StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
       ImageData imageData =storageRepository.save(ImageData.builder().
                name(file.getOriginalFilename()).type(file.getContentType()).
                imageData(ImageUtils.compressImage(file.getBytes())).build());
       if (imageData!=null)
       {
           return "file uploaded successfully: "+file.getOriginalFilename();
       }
       return null;
    }
    public byte[] downloadImage(String name)
    {
        Optional<ImageData> imageData=storageRepository.findByName(name);
       byte[] images= ImageUtils.decompressImage(imageData.get().getImageData());
       return images;
    }
}
