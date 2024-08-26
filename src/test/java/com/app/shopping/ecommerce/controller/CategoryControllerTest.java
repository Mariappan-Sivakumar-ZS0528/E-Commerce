package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CategoryDto;
import com.app.shopping.ecommerce.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    CategoryDto categoryDto;
    byte[] image=new byte[1];
    MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        categoryDto = new CategoryDto();
        file= Mockito.mock(MultipartFile.class);
        Mockito.when(categoryService.addCategory(categoryDto)).thenReturn(categoryDto);
        Mockito.when(categoryService.getAllCategories()).thenReturn(List.of(categoryDto));
        Mockito.when(categoryService.getCategory(1L)).thenReturn(categoryDto);
         Mockito.when(categoryService.updateCategory( categoryDto,1L)).thenReturn(categoryDto);
        Mockito.when(categoryService.updateCategoryImage(file,file,file, 1L)).thenReturn("Category image updated successfully");
        Mockito.when(categoryService.downloadMobileImage(1L,"test")).thenReturn(image);
        Mockito.when(categoryService.downloadDesktopImage(1L,"test")).thenReturn(image);
        Mockito.when(categoryService.downloadThumbnailImage(1L,"test")).thenReturn(image);
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void addCategory() {
        ResponseEntity<CategoryDto> responseEntity = categoryController.addCategory(categoryDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(categoryDto, responseEntity.getBody());
    }

    @Test
    void getAllCategories() {
        ResponseEntity<List<CategoryDto>> responseEntity = categoryController.getAllCategories();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(categoryDto), responseEntity.getBody());
    }

    @Test
    void getCategory() {
        ResponseEntity<CategoryDto> responseEntity = categoryController.getCategory(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoryDto, responseEntity.getBody());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void deleteCategory() {
        assertEquals("Category deleted successfully", categoryController.deleteCategory(1L));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void updateCategory() {
        ResponseEntity<CategoryDto> responseEntity = categoryController.updateCategory( 1L,categoryDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoryDto, responseEntity.getBody());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void updateCategoryImage() throws IOException {
        ResponseEntity<String> responseEntity = categoryController.updateCategoryImage(file,file,file, 1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Category image updated successfully", responseEntity.getBody());
    }

    @Test
    void downloadMobileImage() {
        ResponseEntity<byte[]> responseEntity = categoryController.downloadMobileImage("test",1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(image, responseEntity.getBody());
    }

    @Test
    void downloadDesktopImage() {
        ResponseEntity<byte[]> responseEntity = categoryController.downloadDesktopImage("test",1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(image, responseEntity.getBody());
    }

    @Test
    void downloadThumbnailImage() {
        ResponseEntity<byte[]> responseEntity = categoryController.downloadThumbnailImage("test",1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(image, responseEntity.getBody());
    }
}