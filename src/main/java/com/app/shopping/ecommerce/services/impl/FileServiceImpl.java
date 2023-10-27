package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService
{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
//        File Name
        String name= file.getOriginalFilename();

//        abc.png, random name generate file
        String randomId= UUID.randomUUID().toString();
        String fileName1= randomId.concat(name.substring(name.lastIndexOf(".")));

//        File Path
        String filePath=path+"/"+fileName1;
//        create a folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
//        file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }
}
