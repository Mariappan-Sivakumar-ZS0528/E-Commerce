package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FullwidthDisplayFeaturedPromosControllerTest {
    @Mock
    private FullwidthDisplayFeaturedPromosService fullwidthDisplayFeaturedPromosService;
    @Mock
    private MultipartFile mobileImage;
    @Mock
    private MultipartFile desktopImage;
    @InjectMocks
    private FullwidthDisplayFeaturedPromosController fullwidthDisplayFeaturedPromosController;
    @Test
    void getpromos() throws ParseException {
        FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto = new FullwidthDisplayFeaturedPromosDto();
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        fullwidthDisplayFeaturedPromosDto.setId(1L);
        fullwidthDisplayFeaturedPromosDto.setStartingDate(startingDate);
        fullwidthDisplayFeaturedPromosDto.setEndingDate(endingDate);
        fullwidthDisplayFeaturedPromosDto.setPromoTitle("test");
        fullwidthDisplayFeaturedPromosDto.setLink("test");
        when(fullwidthDisplayFeaturedPromosService.getall()).thenReturn(List.of(fullwidthDisplayFeaturedPromosDto));
        List<FullwidthDisplayFeaturedPromosDto> actual = fullwidthDisplayFeaturedPromosController.getpromos().getBody();
        assertEquals(List.of(fullwidthDisplayFeaturedPromosDto),actual);
    }

    @Test
    void addPromos() throws ParseException {
        FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto = new FullwidthDisplayFeaturedPromosDto();
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        fullwidthDisplayFeaturedPromosDto.setId(1L);
        fullwidthDisplayFeaturedPromosDto.setStartingDate(startingDate);
        fullwidthDisplayFeaturedPromosDto.setEndingDate(endingDate);
        fullwidthDisplayFeaturedPromosDto.setPromoTitle("test");
        fullwidthDisplayFeaturedPromosDto.setLink("test");
        when(fullwidthDisplayFeaturedPromosService.add(fullwidthDisplayFeaturedPromosDto)).thenReturn(fullwidthDisplayFeaturedPromosDto);
        FullwidthDisplayFeaturedPromosDto actual = fullwidthDisplayFeaturedPromosController.addPromos(fullwidthDisplayFeaturedPromosDto).getBody();
        assertEquals(fullwidthDisplayFeaturedPromosDto,actual);
    }

    @Test
    void uploadImages() throws IOException {
        byte[] mobileImageBytes = new byte[]{1, 2, 3};
        byte[] desktopImageBytes = new byte[]{1, 2, 3};
        when(mobileImage.getBytes()).thenReturn(mobileImageBytes);
        when(desktopImage.getBytes()).thenReturn(desktopImageBytes);
        when(fullwidthDisplayFeaturedPromosService.uploadImages(1L, mobileImageBytes, desktopImageBytes)).thenReturn("success");
        String actual = fullwidthDisplayFeaturedPromosController.uploadImages(1L, mobileImage, desktopImage).getBody();
        assertEquals("success", actual);
    }

    @Test
    void downloadMobileImage() {
        byte[] expected = new byte[]{1, 2, 3};
        when(fullwidthDisplayFeaturedPromosService.downloadMobileImage(1L)).thenReturn(Optional.of(expected));
        Optional<byte[]> actual = fullwidthDisplayFeaturedPromosController.downloadMobileImage(1L).getBody();
        assertEquals(expected, actual.get());
    }

    @Test
    void getMeasurement() {
        when(fullwidthDisplayFeaturedPromosService.getMeasurement(1L, "mobile")).thenReturn(String.valueOf(1));
        when(fullwidthDisplayFeaturedPromosService.getMeasurement(1L, "desktop")).thenReturn(String.valueOf(2));
        String actual = fullwidthDisplayFeaturedPromosController.getMeasurement(1L, "mobile").getBody();
        assertEquals("1", actual);
        actual = fullwidthDisplayFeaturedPromosController.getMeasurement(1L, "desktop").getBody();
        assertEquals("2", actual);
    }

    @Test
    void deletePromos() {
        when(fullwidthDisplayFeaturedPromosService.deletePromos(1L)).thenReturn("success");
        String actual = fullwidthDisplayFeaturedPromosController.deletePromos(1L).getBody();
        assertEquals("success", actual);
    }

    @Test
    void downloadDesktopImage() {
        byte[] expected = new byte[]{1, 2, 3};
        when(fullwidthDisplayFeaturedPromosService.downloadDesktopImage(1L)).thenReturn(Optional.of(expected));
        Optional<byte[]> actual = fullwidthDisplayFeaturedPromosController.downloadDesktopImage(1L).getBody();
        assertEquals(expected, actual.get());
    }

    @Test
    void disablePromos() {
        when(fullwidthDisplayFeaturedPromosService.disablePromos(1L)).thenReturn("success");
        String actual = fullwidthDisplayFeaturedPromosController.disablePromos(1L).getBody();
        assertEquals("success", actual);
    }

    @Test
    void enablePromos() {
        when(fullwidthDisplayFeaturedPromosService.enablePromos(1L)).thenReturn("success");
        String actual = fullwidthDisplayFeaturedPromosController.enablePromos(1L).getBody();
        assertEquals("success", actual);
    }

    @Test
    void updateWholePromos() throws ParseException {
        FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto = new FullwidthDisplayFeaturedPromosDto();
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        fullwidthDisplayFeaturedPromosDto.setId(1L);
        fullwidthDisplayFeaturedPromosDto.setStartingDate(startingDate);
        fullwidthDisplayFeaturedPromosDto.setEndingDate(endingDate);
        fullwidthDisplayFeaturedPromosDto.setPromoTitle("test");
        fullwidthDisplayFeaturedPromosDto.setLink("test");
        when(fullwidthDisplayFeaturedPromosService.updateWholePromos(1L, fullwidthDisplayFeaturedPromosDto)).thenReturn(Optional.of(fullwidthDisplayFeaturedPromosDto));
        Optional<FullwidthDisplayFeaturedPromosDto> actual = fullwidthDisplayFeaturedPromosController.updateWholePromos(1L, fullwidthDisplayFeaturedPromosDto).getBody();
        assertEquals(fullwidthDisplayFeaturedPromosDto,actual.get());
    }

}