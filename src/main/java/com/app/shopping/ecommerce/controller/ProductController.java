package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ProductDto;
import com.app.shopping.ecommerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ProductDto createProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        return productService.createProduct(productDto, request);
    }
}
