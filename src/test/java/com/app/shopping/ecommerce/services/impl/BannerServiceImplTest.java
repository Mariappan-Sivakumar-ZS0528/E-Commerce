package com.app.shopping.ecommerce.services.impl;
import com.app.shopping.ecommerce.entity.Banner;
import com.app.shopping.ecommerce.payload.AddressBookDto;
import com.app.shopping.ecommerce.payload.BannerLinkDto;
import com.app.shopping.ecommerce.repository.BannerRepository;
import com.app.shopping.ecommerce.services.BannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BannerServiceImplTest {

    @Autowired
    private BannerService   bannerService;
    @MockBean
    private BannerRepository bannerRepository;
    @BeforeEach
    public void setUp() {
        Banner banner = new Banner();
        banner.setId(1L);
        banner.setDesktopLink("https://www.google.com");
        banner.setMobileLink("https://www.google.com");
        banner.setTitle("Banner Title");
        List<Banner> banners = List.of(banner);
        Mockito.when(bannerRepository.findById(1L)).thenReturn(Optional.of(banner));
        Mockito.when(bannerRepository.save(Mockito.any(Banner.class))).thenReturn(banner);
    }
    @Test
    public void testUploadLink() throws IOException {

        BannerLinkDto bannerLinkDto = new BannerLinkDto();
        bannerLinkDto.setId(1L);
        bannerLinkDto.setDesktopLink("https://www.google.com");
        bannerLinkDto.setMobileLink("https://www.google.com");
        bannerLinkDto.setTitle("Banner Title");
        BannerLinkDto bannerLinkDto1 = bannerService.uploadLink(bannerLinkDto);
        assertEquals("https://www.google.com", bannerLinkDto1.getDesktopLink());
        assertEquals("https://www.google.com", bannerLinkDto1.getMobileLink());
    }
}