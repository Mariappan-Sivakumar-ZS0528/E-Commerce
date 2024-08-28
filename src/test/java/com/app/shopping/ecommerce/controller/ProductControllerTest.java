package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ProductDto;
import com.app.shopping.ecommerce.payload.ProductOfferDto;
import com.app.shopping.ecommerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @Test
    void createProduct() {
        ProductDto productDto = new ProductDto();
        HttpServletRequest request = new MockHttpServletRequest();
        when(productService.createProduct(productDto, request)).thenReturn(productDto);
        ProductDto actual = productController.createProduct(productDto, request).getBody();
        assertEquals(productDto, actual);
    }

    @Test
    void getAllProducts() {
        ProductDto productDto = new ProductDto();
        when(productService.getAllProducts()).thenReturn(List.of(productDto));
        List<ProductDto> actual = productController.getAllProducts().getBody();
        assertEquals(List.of(productDto), actual);
    }

    @Test
    void testGetAllProducts() {
        ProductDto productDto = new ProductDto();
        when(productService.getProductsBySupplier(1L)).thenReturn(List.of(productDto));
        List<ProductDto> actual = productController.getAllProducts(1L).getBody();
        assertEquals(List.of(productDto), actual);
    }

    @Test
    void getByCategoryAndSupplier() {
        ProductDto productDto = new ProductDto();
        when(productService.getByCategoryAndSupplier(1L, "test")).thenReturn(List.of(productDto));
        List<ProductDto> actual = productController.getByCategoryAndSupplier(1L, "test").getBody();
        assertEquals(List.of(productDto), actual);
    }

    @Test
    void getBySubCategoryAndSupplier() {
        ProductDto productDto = new ProductDto();
        when(productService.getBySubCategoryAndSupplier(1L, "test")).thenReturn(List.of(productDto));
        List<ProductDto> actual = productController.getBySubCategoryAndSupplier(1L, "test").getBody();
        assertEquals(List.of(productDto), actual);
    }

    @Test
    void searchProduct() {
        ProductDto productDto = new ProductDto();
        when(productService.searchProduct("test")).thenReturn(List.of(productDto));
        List<ProductDto> actual = productController.searchProduct("test").getBody();
        assertEquals(List.of(productDto), actual);
    }

    @Test
    void searchProductInSupplier() {
        ProductDto productDto = new ProductDto();
        when(productService.searchProductInSupplier(1L, "test")).thenReturn(List.of(productDto));
        List<ProductDto> actual = productController.searchProductInSupplier(1L, "test").getBody();
        assertEquals(List.of(productDto), actual);
    }

    @Test
    void getProductById() {
        ProductDto productDto = new ProductDto();
        when(productService.getProductById(1L)).thenReturn(productDto);
        ProductDto actual = productController.getProductById(1L).getBody();
        assertEquals(productDto, actual);
    }

    @Test
    void updateProduct() {
        ProductDto productDto = new ProductDto();
        HttpServletRequest request = new MockHttpServletRequest();
        when(productService.updateProduct(1L, productDto, request)).thenReturn(productDto);
        ProductDto actual = productController.updateProduct(1L, productDto, request).getBody();
        assertEquals(productDto, actual);
    }

    @Test
    void deleteProductForSuccess() {
        HttpServletRequest request = new MockHttpServletRequest();
        when(productService.deleteProduct(1L, request)).thenReturn(true);
        String actual = productController.deleteProduct(1L, request).getBody();
        assertEquals("Product deleted successfully", actual);
    }
    @Test
    void deleteProductForFailure() {
        HttpServletRequest request = new MockHttpServletRequest();
        when(productService.deleteProduct(1L, request)).thenReturn(false);
        String actual = productController.deleteProduct(1L, request).getBody();
        assertEquals("Product cannot be deleted", actual);
    }

    @Test
    void increaseUnits() {
        HttpServletRequest request = new MockHttpServletRequest();
        ProductDto productDto = new ProductDto();
        when(productService.increaseUnits(1L, request, 1)).thenReturn(productDto);
        ProductDto actual = productController.increaseUnits(1L, request, 1).getBody();
        assertEquals(productDto, actual);
    }

    @Test
    void decreaseUnits() {
        HttpServletRequest request = new MockHttpServletRequest();
        ProductDto productDto = new ProductDto();
        when(productService.decreaseUnits(1L, request, 1)).thenReturn(productDto);
        ProductDto actual = productController.decreaseUnits(1L, request, 1).getBody();
        assertEquals(productDto, actual);
    }

    @Test
    void uploadImage() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("test", "test".getBytes());
        HttpServletRequest request = new MockHttpServletRequest();
        when(productService.uploadImage(1L,request, multipartFile)).thenReturn("success");
        String actual = productController.uploadImage(1L,request,multipartFile).getBody();
        assertEquals("success", actual);
    }

    @Test
    void downloadImage() {
        byte[] mock=new byte[]{1, 2, 3, 4, 5};
        when(productService.downloadImage(1L, "test")).thenReturn(mock);
        byte[] actual = productController.downloadImage(1L, "test").getBody();
        assertEquals(mock, actual);
    }

    @Test
    void createProductOffer() {
        ProductOfferDto productOfferDto = new ProductOfferDto();
        HttpServletRequest request = new MockHttpServletRequest();
        when(productService.createProductOffer(1L, productOfferDto, request)).thenReturn(productOfferDto);
        ProductOfferDto actual = productController.createProductOffer(1L, productOfferDto, request).getBody();
        assertEquals(productOfferDto, actual);
    }

    @Test
    void getProductOfferById() {
        ProductOfferDto productOfferDto = new ProductOfferDto();
        when(productService.getProductOfferById(1L)).thenReturn(productOfferDto);
        ProductOfferDto actual = productController.getProductOfferById(1L).getBody();
        assertEquals(productOfferDto, actual);
    }
}