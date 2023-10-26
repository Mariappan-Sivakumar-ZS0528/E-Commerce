package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FileResponse;
import com.app.shopping.ecommerce.services.FileService;
import com.app.shopping.ecommerce.services.StorageService;
import com.app.shopping.ecommerce.services.StorageService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController
{
//    @Autowired
//    private FileService fileService;
//    @Value("${project.image}")
//    private String path;
//
//    @PostMapping("/upload")
//    public ResponseEntity<FileResponse> upload(@RequestParam("image") MultipartFile image)
//    {
//        String fileName= null;
//        try {
//            fileName = this.fileService.uploadImage(path,image);
//
//            // repository call
//
//            //file name
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(new FileResponse(null,"Image is not uploaded!!"),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>(new FileResponse(fileName,"Image is successfully uploaded!!"),HttpStatus.OK);
//    }
//-----------------------------------------------------------------------------------------------------------------------------------------------
    @Autowired
    private StorageService2 storageService;
    @PostMapping
    public ResponseEntity<String > uploadImage(@RequestParam("mobileImage") MultipartFile mobileImage,
                                               @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException {
        String uploadedFileName = storageService.uploadImage(mobileImage,desktopImage);
        return ResponseEntity.status(HttpStatus.OK).body(uploadedFileName);
    }
    @GetMapping("/mobileImage/{fileName}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable String fileName) throws IOException {
        byte[] images = storageService.downloadMobileImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }
    @GetMapping("/desktopImage/{fileName}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable String fileName) throws IOException {
        byte[] images = storageService.downloadDesktopImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }

}
