package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.DisplayInTilesPromosDto;
import com.app.shopping.ecommerce.services.DisplayInTilesPromosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DisplayInTilesPromosControllerTest {
    @Autowired
    DisplayInTilesPromosController displayInTilesPromosController;
    @MockBean
    DisplayInTilesPromosService displayInTilesPromosService;
    DisplayInTilesPromosDto displayInTilesPromosDto;
    byte[] image=new byte[1];
    MultipartFile file;
    @BeforeEach
    void setUp() throws IOException {

        file= Mockito.mock(MultipartFile.class);
        displayInTilesPromosDto = new DisplayInTilesPromosDto();
        Mockito.when(displayInTilesPromosService.getall()).thenReturn(List.of(displayInTilesPromosDto));
        Mockito.when(displayInTilesPromosService.add(displayInTilesPromosDto)).thenReturn(displayInTilesPromosDto);
        Mockito.when(file.getBytes()).thenReturn(image);
        Mockito.when(displayInTilesPromosService.uploadImages(1L,image,image)).thenReturn("file uploaded successfully: test test");
        Mockito.when(displayInTilesPromosService.downloadMobileImage(1L)).thenReturn(image);
        Mockito.when(displayInTilesPromosService.downloadDesktopImage(1L)).thenReturn(image);
        Mockito.when(displayInTilesPromosService.uploadImages(1L,image,image)).thenReturn("file uploaded successfully: test test");
        Mockito.when(displayInTilesPromosService.getMeasurement(1L,"test")).thenReturn("100x100");
        Mockito.when(displayInTilesPromosService.deletePromos(1L)).thenReturn("Promos deleted successfully");
        Mockito.when(displayInTilesPromosService.disablePromos(1L)).thenReturn("Promos disabled successfully");
        Mockito.when(displayInTilesPromosService.enablePromos(1L)).thenReturn("Promos enabled successfully");
        Mockito.when(displayInTilesPromosService.updateWholePromos(1L,displayInTilesPromosDto)).thenReturn(displayInTilesPromosDto);
    }

    @Test
    void getpromos() {
        ResponseEntity<List<DisplayInTilesPromosDto>> responseEntity = displayInTilesPromosController.getpromos();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(displayInTilesPromosDto), responseEntity.getBody());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void addPromos() {
        ResponseEntity<DisplayInTilesPromosDto> responseEntity = displayInTilesPromosController.addPromos(displayInTilesPromosDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(displayInTilesPromosDto, responseEntity.getBody());
    }
    @WithMockUser(roles = "ADMIN")
    @Test
    void uploadImages() throws IOException {
        ResponseEntity<String> responseEntity = displayInTilesPromosController.uploadImages(1L, file, file);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("file uploaded successfully: test test",responseEntity.getBody());
    }

    @Test
    void downloadMobileImage() {
        ResponseEntity<byte[]> responseEntity = displayInTilesPromosController.downloadMobileImage(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(image, responseEntity.getBody());
    }

    @Test
    void getMeasurement() {
        ResponseEntity<String> responseEntity = displayInTilesPromosController.getMeasurement(1L,"test");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("100x100",responseEntity.getBody());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void deletePromos() {
        ResponseEntity<String> responseEntity = displayInTilesPromosController.deletePromos(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Promos deleted successfully",responseEntity.getBody());
    }

    @Test
    void downloadDesktopImage() {
        ResponseEntity<byte[]> responseEntity = displayInTilesPromosController.downloadDesktopImage(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(image, responseEntity.getBody());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void disablePromos() {
        ResponseEntity<String> responseEntity = displayInTilesPromosController.disablePromos(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Promos disabled successfully",responseEntity.getBody());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void enablePromos() {
        ResponseEntity<String> responseEntity = displayInTilesPromosController.enablePromos(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Promos enabled successfully",responseEntity.getBody());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void updateWholePromos() {
        ResponseEntity<DisplayInTilesPromosDto> responseEntity = displayInTilesPromosController.updateWholePromos(1L,displayInTilesPromosDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(displayInTilesPromosDto, responseEntity.getBody());
    }
}