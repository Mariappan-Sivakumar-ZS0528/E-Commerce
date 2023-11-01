package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.BannerLinkDto;
import com.app.shopping.ecommerce.services.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/banner")
@Tag(name = "Banner", description = " CRUD Operations related to Banner")
public class BannerController
{
    @Autowired
    private BannerService bannerService;
    @Operation(summary = "Upload Banner Image")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String > uploadImage(@RequestParam("mobileImage") MultipartFile mobileImage,
                                               @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException {
        String uploadedFileName = bannerService.uploadImage(mobileImage,desktopImage);
        return ResponseEntity.status(HttpStatus.OK).body(uploadedFileName);
    }
    @Operation(summary = "Download Banner Mobile Image")
    @GetMapping("/{id}/mobileImage/{fileName}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable(value = "id")Long id, @PathVariable String fileName) throws IOException
     {
        byte[] images = bannerService.downloadMobileImage(id,fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }
    @Operation(summary = "Download Banner Desktop Image")
    @GetMapping("/{id}/desktopImage/{fileName}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable(value = "id")Long id, @PathVariable String fileName) throws IOException {
        byte[] images = bannerService.downloadDesktopImage(id,fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }
    @Operation(summary = "Upload Banner Link")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/link")
    public ResponseEntity<BannerLinkDto> uploadLink(@RequestBody BannerLinkDto bannerLinkDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bannerService.uploadLink(bannerLinkDto));
    }
    @Operation(summary = "Update Banner Images")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
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
