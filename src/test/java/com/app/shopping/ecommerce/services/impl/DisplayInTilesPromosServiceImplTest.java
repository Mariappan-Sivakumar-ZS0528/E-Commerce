package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.DisplayInTilesPromos;
import com.app.shopping.ecommerce.payload.DisplayInTilesPromosDto;
import com.app.shopping.ecommerce.payload.ImageMeasurementsDto;
import com.app.shopping.ecommerce.repository.DisplayInTilesPromosRepository;
import com.app.shopping.ecommerce.services.DisplayInTilesPromosService;
import com.app.shopping.ecommerce.util.ImageDimensionExtractor;
import com.app.shopping.ecommerce.util.ImageDimensions;
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

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DisplayInTilesPromosServiceImplTest {
    @MockBean
    private DisplayInTilesPromosRepository displayInTilesPromosRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private DisplayInTilesPromosService displayInTilesPromosService;
    DisplayInTilesPromos displayInTilesPromos;
    List<DisplayInTilesPromos> displayInTilesPromosList;
    DisplayInTilesPromosDto displayInTilesPromosDto;
    ImageMeasurementsDto imageMeasurementsDto;
    ImageDimensionExtractor imageDimensionExtractor;
    static ImageDimensions imageDimensions=new ImageDimensions(100,100);
    static byte[] bytes=new byte[1];
    private static MockedStatic<ImageUtils> mockedStaticImageUtils;

    @BeforeAll
    static void beforeAll() throws IOException {
        if (mockedStaticImageUtils == null) {
            mockedStaticImageUtils = Mockito.mockStatic(ImageUtils.class);
            Mockito.when(ImageUtils.compressImage(Mockito.any(byte[].class))).thenReturn(bytes);
            Mockito.when(ImageUtils.decompressImage(Mockito.any(byte[].class))).thenReturn(bytes);
        }



        Mockito.mockStatic(ImageDimensionExtractor.class);
        Mockito.when(ImageDimensionExtractor.getImageDimensions(Mockito.any(byte[].class))).thenReturn(imageDimensions);
    }

    @AfterAll
    static void afterAll() {
//        mockedStaticImageUtils.close();
    }
    @BeforeEach
    void setUp() {
        displayInTilesPromos = new DisplayInTilesPromos();
        displayInTilesPromos.setId(1L);
        displayInTilesPromos.setDisplayPromoTitle("test");
        displayInTilesPromos.setDesktopImage(bytes);
        displayInTilesPromos.setMobileImage(bytes);
        displayInTilesPromos.setStartingDate(new Date());
        displayInTilesPromos.setDesktopheight(100);
        displayInTilesPromos.setMobileheight(100);
        displayInTilesPromos.setDesktopwidth(100);
        displayInTilesPromos.setMobilewidth(100);
        displayInTilesPromosList = List.of(displayInTilesPromos);
        displayInTilesPromosDto = new DisplayInTilesPromosDto(1L, "test", new Date(), new Date(),"link");
        imageMeasurementsDto=new ImageMeasurementsDto(100,100,100,100);
        Mockito.when(displayInTilesPromosRepository.findAll()).thenReturn(displayInTilesPromosList);
        Mockito.when(displayInTilesPromosRepository.findById(1L)).thenReturn(Optional.of(displayInTilesPromos));
        Mockito.when(displayInTilesPromosRepository.save(Mockito.any(DisplayInTilesPromos.class))).thenReturn(displayInTilesPromos);

        Mockito.when(modelMapper.map(displayInTilesPromos, DisplayInTilesPromosDto.class)).thenReturn(displayInTilesPromosDto);
        Mockito.when(modelMapper.map(displayInTilesPromosDto, DisplayInTilesPromos.class)).thenReturn(displayInTilesPromos);
        Mockito.when(modelMapper.map(displayInTilesPromos, ImageMeasurementsDto.class)).thenReturn(imageMeasurementsDto);
    }

    @Test
    void getall() {
        List<DisplayInTilesPromosDto> actual = displayInTilesPromosService.getall();
        assertEquals(displayInTilesPromosList.get(0).getId(), actual.get(0).getId());
    }

    @Test
    void add() {
        DisplayInTilesPromosDto actual = displayInTilesPromosService.add(displayInTilesPromosDto);
        assertEquals(displayInTilesPromosDto.getId(), actual.getId());
    }

    @Test
    void downloadMobileImage() {
        assertEquals(bytes, displayInTilesPromosService.downloadMobileImage(1L));
    }
    @Test
    void downloadMobileImageReturnNull() {
        assertEquals(null, displayInTilesPromosService.downloadMobileImage(2L));
    }


    @Test
    void downloadDesktopImage() {
        assertEquals(bytes, displayInTilesPromosService.downloadDesktopImage(1L));
    }
    @Test
    void downloadDesktopImageReturnNull() {
        assertEquals(null, displayInTilesPromosService.downloadDesktopImage(2L));
    }

    @Test
    void getMeasurementForDesktop() {
        assertEquals("(100×100)",displayInTilesPromosService.getMeasurement(1L, "desktop"));
    }

    @Test
    void getMeasurementForMobile() {
        assertEquals("(100×100)",displayInTilesPromosService.getMeasurement(1L, "mobile"));
    }

    @Test
    void getMeasurementForElse() {
        assertEquals("There is only mobile and desktop measurements",displayInTilesPromosService.getMeasurement(1L, "else"));
    }

    @Test
    void getMeasurementForElse2() {
        assertEquals("No record found in the provided id",displayInTilesPromosService.getMeasurement(2L, "else"));
    }

    @Test
    void deletePromos() {
        assertEquals("Promo deleted successfully",displayInTilesPromosService.deletePromos(1L));
    }

    @Test
    void disablePromos() {
        assertEquals("Promo disabled successfully",displayInTilesPromosService.disablePromos(1L));
    }

    @Test
    void disablePromosReturnNull() {
        assertEquals("No record found in the provided id",displayInTilesPromosService.disablePromos(2L));
    }

    @Test
    void enablePromos() {
        assertEquals("Promo enabled successfully",displayInTilesPromosService.enablePromos(1L));
    }

    @Test
    void enablePromosReturnNull() {
        assertEquals("No record found in the provided id",displayInTilesPromosService.enablePromos(2L));
    }

    @Test
    void updateWholePromos() {
        DisplayInTilesPromosDto actual = displayInTilesPromosService.updateWholePromos(1L, displayInTilesPromosDto);
        assertEquals(displayInTilesPromosDto.getId(), actual.getId());
    }

    @Test
    void updateWholePromosReturnNull() {
        assertEquals(null,displayInTilesPromosService.updateWholePromos(2L, displayInTilesPromosDto));
    }

    @Test
    void uploadImages() throws IOException {
        assertEquals("Images uploaded successfully",displayInTilesPromosService.uploadImages(1L, bytes, bytes));
    }

    @Test
    void uploadImagesReturnNull() throws IOException {
        assertThrows(RuntimeException.class, () -> displayInTilesPromosService.uploadImages(2L, bytes, bytes));
    }
}