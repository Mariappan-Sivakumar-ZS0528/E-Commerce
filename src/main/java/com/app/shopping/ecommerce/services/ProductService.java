package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.ProductDto;
import com.app.shopping.ecommerce.payload.ProductOfferDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, HttpServletRequest request);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    List<ProductDto> getProductsBySupplier(Long id);
    List<ProductDto> getByCategoryAndSupplier(Long id,String category);
    List<ProductDto> getBySubCategoryAndSupplier(Long id,String subCategory);
    List<ProductDto> searchProduct(String query);
    List<ProductDto> searchProductInSupplier(Long id,String query);
    ProductDto updateProduct(Long productId,ProductDto productDto, HttpServletRequest request);
    boolean deleteProduct(Long productId, HttpServletRequest request);
    ProductDto increaseUnits(Long productId, HttpServletRequest request,int units);
    ProductDto decreaseUnits(Long productId, HttpServletRequest request,int units);
    String uploadImage(Long productId, HttpServletRequest request, MultipartFile... images) throws IOException;
    byte[] downloadImage(Long productId, String imageName);
    ProductOfferDto createProductOffer(Long productId,ProductOfferDto productOfferDto, HttpServletRequest request);
    ProductOfferDto getProductOfferById(Long productId);

}
