package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.BannerLinkDto;
import com.app.shopping.ecommerce.services.BannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BannerControllerTest {
    @MockBean
    BannerService bannerService;
    @Autowired
    BannerController bannerController;

    MultipartFile file;
    BannerLinkDto bannerLinkDto;
    byte[] image=new byte[1];

    @BeforeEach
    void setUp() throws IOException {
        file= Mockito.mock(MultipartFile.class);
        bannerLinkDto = new BannerLinkDto();
        Mockito.when(bannerService.uploadImage(file,file)).thenReturn("file uploaded successfully: test test");
        Mockito.when(bannerService.downloadMobileImage(1L,"test")).thenReturn(image);
        Mockito.when(bannerService.downloadDesktopImage(1L,"test")).thenReturn(image);
        Mockito.when(bannerService.uploadLink(bannerLinkDto)).thenReturn(bannerLinkDto);
        Mockito.when(bannerService.updateBannerImages(1L,file,file)).thenReturn("Banner images updated successfully: test test");
    }

    @Test
    void uploadImage() throws IOException {
        ResponseEntity<String> responseEntity = bannerController.uploadImage(file, file);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("file uploaded successfully: test test",responseEntity.getBody());
    }

    @Test
    void downloadMobileImage() throws IOException {
        ResponseEntity<byte[]> responseEntity = bannerController.downloadMobileImage(1L,"test");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(image, responseEntity.getBody());
    }

    @Test
    void downloadDesktopImage() throws IOException {
        ResponseEntity<byte[]> responseEntity = bannerController.downloadDesktopImage(1L,"test");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(image, responseEntity.getBody());
    }

    @Test
    void uploadLink() {
        ResponseEntity<BannerLinkDto> responseEntity = bannerController.uploadLink(bannerLinkDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bannerLinkDto, responseEntity.getBody());
    }

    @Test
    void updateBannerImages() throws IOException {
        ResponseEntity<String> responseEntity = bannerController.updateBannerImages(1L, file, file);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Banner images updated successfully: test test",responseEntity.getBody());
    }

    @Test
    void updateBannerImagesWithNull() throws IOException {
        Mockito.when(bannerService.updateBannerImages(1L,null,null)).thenReturn("not found");
        ResponseEntity<String> responseEntity = bannerController.updateBannerImages(1L, null, null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}