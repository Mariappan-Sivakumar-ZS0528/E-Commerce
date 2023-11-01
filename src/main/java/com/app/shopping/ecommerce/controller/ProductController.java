package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ProductDto;
import com.app.shopping.ecommerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/supplier/{supplierId}")
    public List<ProductDto> getAllProducts(@PathVariable Long supplierId){
        return productService.getProductsBySupplier(supplierId);
    }
    @GetMapping("/supplier/{supplierId}/category/{category}")
    public List<ProductDto> getByCategoryAndSupplier(@PathVariable Long supplierId, @PathVariable String category){
        return productService.getByCategoryAndSupplier(supplierId, category);
    }
    @GetMapping("/supplier/{supplierId}/subcategory/{subCategory}")
    public List<ProductDto> getBySubCategoryAndSupplier(@PathVariable Long supplierId, @PathVariable String subCategory){
        return productService.getBySubCategoryAndSupplier(supplierId, subCategory);
    }
    @GetMapping("/supplier/{supplierId}/search")
    public List<ProductDto> searchProductInSupplier(@PathVariable Long supplierId, @RequestParam("query") String query){
        return productService.searchProductInSupplier(supplierId, query);
    }
    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto, HttpServletRequest request){
        return productService.updateProduct(productId, productDto, request);
    }
    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId, HttpServletRequest request){
        productService.deleteProduct(productId, request);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
