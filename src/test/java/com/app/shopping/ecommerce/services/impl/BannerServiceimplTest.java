package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Banner;
import com.app.shopping.ecommerce.payload.BannerLinkDto;
import com.app.shopping.ecommerce.repository.BannerRepository;
import com.app.shopping.ecommerce.services.BannerService;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BannerServiceimplTest {

    @MockBean
    private BannerRepository bannerRepository;
    @MockBean
    private ModelMapper modelMapper;
     @Autowired
     private BannerService bannerService;

     Banner banner;
     BannerLinkDto bannerLinkDto;

     MultipartFile multipartFile;
    private static MockedStatic<ImageUtils> mockedStaticImageUtils;

    static byte[] bytes=new byte[1];

    @BeforeAll
    static void beforeAll() {
        if (mockedStaticImageUtils == null) {
            mockedStaticImageUtils = Mockito.mockStatic(ImageUtils.class);
            Mockito.when(ImageUtils.compressImage(Mockito.any(byte[].class))).thenReturn(bytes);
            Mockito.when(ImageUtils.decompressImage(Mockito.any(byte[].class))).thenReturn(bytes);
        }
    }

    @AfterAll
    static void afterAll() {
        mockedStaticImageUtils.close();
    }

    @BeforeEach
    void setUp() {
        multipartFile=new MockMultipartFile("test","test","image/png",bytes);
        banner=new Banner();
        banner.setId(1L);
        banner.setDesktopImageName("desktop.png");
        banner.setMobileImageName("mobile.png");
        banner.setImageType("image/png");
        banner.setMobileImageData(bytes);
        banner.setDesktopImageData(bytes);
        banner.setTitle("test");
        banner.setDesktopLink("test");
        banner.setMobileLink("test");

        bannerLinkDto=new BannerLinkDto();
        bannerLinkDto.setId(1L);
        bannerLinkDto.setDesktopLink("test");
        bannerLinkDto.setMobileLink("test");
        bannerLinkDto.setTitle("test");
        Mockito.when(bannerRepository.save(Mockito.any(Banner.class))).thenReturn(banner);
        Mockito.when(bannerRepository.findById(1L)).thenReturn(Optional.of(banner));
        Mockito.when(modelMapper.map(banner,BannerLinkDto.class)).thenReturn(bannerLinkDto);
        Mockito.when(modelMapper.map(bannerLinkDto,Banner.class)).thenReturn(banner);
    }

    @Test
    void uploadImage() throws IOException {
        assertEquals("file uploaded successfully: test test", bannerService.uploadImage(multipartFile,multipartFile));
    }

    @Test
    void downloadDesktopImage() {
        assertEquals(bytes,bannerService.downloadDesktopImage(1L,"desktop.png"));
    }

    @Test
    void downloadDesktopImageReturnNull() {
        assertEquals(null,bannerService.downloadDesktopImage(1L,"test.png"));
    }

    @Test
    void downloadMobileImage() {
        assertEquals(bytes,bannerService.downloadMobileImage(1L,"mobile.png"));
    }

    @Test
    void downloadMobileImageReturnNull() {
        assertEquals(null,bannerService.downloadMobileImage(1L,"test.png"));
    }

    @Test
    void uploadLink() {
        assertEquals(bannerLinkDto,bannerService.uploadLink(bannerLinkDto));
    }


    @Test
    void updateBannerImages() throws IOException {
        assertEquals("mobile image uploaded successfully: test/n" +
                " desktop image uploaded successfully: test", bannerService.updateBannerImages(1L,multipartFile,multipartFile));
    }

//    @Test
//    void downloadDesktopImageReturnNulltest() {
//        Mockito.when(bannerRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
//        bannerService.downloadDesktopImage(1L,"test.png");
//
//    }
}