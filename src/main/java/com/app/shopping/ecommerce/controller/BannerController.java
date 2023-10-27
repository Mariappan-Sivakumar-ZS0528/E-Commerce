package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.BannerLinkDto;
import com.app.shopping.ecommerce.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/file")
public class BannerController
{
    @Autowired
    private BannerService bannerService;
    @PostMapping
    public ResponseEntity<String > uploadImage(@RequestParam("mobileImage") MultipartFile mobileImage,
                                               @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException {
        String uploadedFileName = bannerService.uploadImage(mobileImage,desktopImage);
        return ResponseEntity.status(HttpStatus.OK).body(uploadedFileName);
    }
    @GetMapping("/{id}/mobileImage/{fileName}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable(value = "id")Long id, @PathVariable String fileName) throws IOException
     {
        byte[] images = bannerService.downloadMobileImage(id,fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }
    @GetMapping("/{id}/desktopImage/{fileName}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable(value = "id")Long id, @PathVariable String fileName) throws IOException {
        byte[] images = bannerService.downloadDesktopImage(id,fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }
    @PostMapping("/link")
    public ResponseEntity<BannerLinkDto> uploadLink(@RequestBody BannerLinkDto bannerLinkDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bannerService.uploadLink(bannerLinkDto));
    }
    @PostMapping("/{id}/updateImages")
    public ResponseEntity<String> updateBannerImages(
            @PathVariable Long id,
            @RequestParam("mobileImage") MultipartFile mobileImage,
            @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException {
        String result = bannerService.updateBannerImages(id, mobileImage, desktopImage);
        if (result.contains("not found")) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
