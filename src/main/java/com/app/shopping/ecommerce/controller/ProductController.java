package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ProductDto;
import com.app.shopping.ecommerce.payload.ProductOfferDto;
import com.app.shopping.ecommerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;
    Logger logger= LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        return new ResponseEntity<>(productService.createProduct(productDto, request), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable Long supplierId){
        return ResponseEntity.ok(productService.getProductsBySupplier(supplierId));
    }
    @GetMapping("/supplier/{supplierId}/category/{category}")
    public ResponseEntity<List<ProductDto>> getByCategoryAndSupplier(@PathVariable Long supplierId, @PathVariable String category){
        return ResponseEntity.ok(productService.getByCategoryAndSupplier(supplierId, category));
    }
    @GetMapping("/supplier/{supplierId}/subcategory/{subCategory}")
    public ResponseEntity<List<ProductDto>> getBySubCategoryAndSupplier(@PathVariable Long supplierId, @PathVariable String subCategory){
        return ResponseEntity.ok(productService.getBySubCategoryAndSupplier(supplierId, subCategory));
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProduct(@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProduct(query));
    }
    @GetMapping("/supplier/{supplierId}/search")
    public ResponseEntity<List<ProductDto>> searchProductInSupplier(@PathVariable Long supplierId, @RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProductInSupplier(supplierId, query));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }
    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto, HttpServletRequest request){
        return ResponseEntity.ok(productService.updateProduct(productId, productDto, request));
    }
    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId, HttpServletRequest request){
        boolean deleted = productService.deleteProduct(productId, request);
        if (!deleted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product cannot be deleted");
        }
        else {
            return ResponseEntity.ok("Product deleted successfully");
        }
    }
    @PutMapping("/increaseUnits/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<ProductDto> increaseUnits(@PathVariable Long productId, HttpServletRequest request,@RequestParam("units") int units){
        return ResponseEntity.ok(productService.increaseUnits(productId, request, units));
    }
    @PutMapping("/decreaseUnits/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<ProductDto> decreaseUnits(@PathVariable Long productId, HttpServletRequest request,@RequestParam("units") int units){
        return ResponseEntity.ok(productService.decreaseUnits(productId, request, units));
    }
    @PutMapping("/uploadImage/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<String> uploadImage(@PathVariable Long productId, HttpServletRequest request, @RequestParam("images") MultipartFile... images) throws IOException {
        return ResponseEntity.ok(productService.uploadImage(productId, request, images));
    }
    @GetMapping("/downloadImage/{productId}/{imageName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long productId, @PathVariable String imageName){
        logger.info(productId + " " + imageName);
        return ResponseEntity.ok(productService.downloadImage(productId,imageName));
    }
    @PostMapping("/createProductOffer/{productId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<ProductOfferDto> createProductOffer( @PathVariable Long productId,@RequestBody ProductOfferDto productOfferDto, HttpServletRequest request){
        logger.info(productOfferDto.toString());
        return ResponseEntity.ok(productService.createProductOffer(productId,productOfferDto, request));
    }

    @GetMapping("/getProductOffer/{productId}")
    public ResponseEntity<ProductOfferDto> getProductOfferById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductOfferById(productId));
    }
}
