package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.ProductDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, HttpServletRequest request);
    ProductDto getProductById(Long id, HttpServletRequest request);
    List<ProductDto> getProductsBySupplier(Long id);
    List<ProductDto> getByCategoryAndSupplier(Long id,String category);
    List<ProductDto> getBySubCategoryAndSupplier(Long id,String subCategory);
    List<ProductDto> searchProductInSupplier(Long id,String query);
    ProductDto updateProduct(Long productId,ProductDto productDto, HttpServletRequest request);
    void deleteProduct(Long productId, HttpServletRequest request);
    ProductDto increaseUnits(Long productId, HttpServletRequest request,int units);
    ProductDto decreaseUnits(Long productId, HttpServletRequest request,int units);
    String uploadImage(Long productId, HttpServletRequest request, MultipartFile... images) throws IOException;
}
