package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.ProductDto;
import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, HttpServletRequest request);
}
