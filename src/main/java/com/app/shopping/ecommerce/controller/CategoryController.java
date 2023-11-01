package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CategoryDto;
import com.app.shopping.ecommerce.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category Controller", description = "APIs for Category to do CRUD operations")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add Category", description = "Create Category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        return new  ResponseEntity(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Categories", description = "Get All Categories Details")
    @ApiResponse(responseCode = "200", description = "Categories fetched successfully")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @Operation(summary = "Get Category", description = "Get Category Details")
    @ApiResponse(responseCode = "200", description = "Category fetched successfully")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete Category", description = "Delete Category")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category deleted successfully";
    }
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update Category", description = "Update Category")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, id));
    }
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Image upload to Category", description = "Add image to Category")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/image/{id}")
    public ResponseEntity<String> updateCategoryImage(@RequestParam("mobileImage") MultipartFile mobileImage, @RequestParam("desktopImage") MultipartFile desktopImage, @RequestParam("thumbnailImage") MultipartFile thumbnailImage, @PathVariable("id") Long categoryId) throws IOException {
        return ResponseEntity.ok(categoryService.updateCategoryImage(mobileImage, desktopImage, thumbnailImage, categoryId));
    }
    @Operation(summary = "Get Category Mobile Image", description = "Get Category Mobile Image")
    @ApiResponse(responseCode = "200", description = "Category Mobile Image fetched successfully")
    @GetMapping("/image/{id}/mobile/{mobileImageName}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable("mobileImageName") String mobileImageName, @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.downloadMobileImage(id,mobileImageName));
    }
    @Operation(summary = "Get Category Desktop Image", description = "Get Category Desktop Image")
    @ApiResponse(responseCode = "200", description = "Category Desktop Image fetched successfully")
    @GetMapping("/image/{id}/desktop/{desktopImageName}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable("desktopImageName") String desktopImageName, @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.downloadDesktopImage(id,desktopImageName));
    }
    @Operation(summary = "Get Category Thumbnail Image", description = "Get Category Thumbnail Image")
    @ApiResponse(responseCode = "200", description = "Category Thumbnail Image fetched successfully")
    @GetMapping("/image/{id}/thumbnail/{thumbnailImageName}")
    public ResponseEntity<byte[]> downloadThumbnailImage(@PathVariable("thumbnailImageName") String thumbnailImageName, @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.downloadThumbnailImage(id,thumbnailImageName));
    }

}
