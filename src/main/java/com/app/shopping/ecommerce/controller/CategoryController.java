package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CategoryDto;
import com.app.shopping.ecommerce.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        return new  ResponseEntity(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category deleted successfully";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, id));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/image/{id}")
    public ResponseEntity<String> updateCategoryImage(@RequestParam("mobileImage") MultipartFile mobileImage, @RequestParam("desktopImage") MultipartFile desktopImage, @RequestParam("thumbnailImage") MultipartFile thumbnailImage, @PathVariable("id") Long categoryId) throws IOException {
        return ResponseEntity.ok(categoryService.updateCategoryImage(mobileImage, desktopImage, thumbnailImage, categoryId));
    }
    @GetMapping("/image/{id}/mobile/{mobileImageName}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable("mobileImageName") String mobileImageName, @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.downloadMobileImage(id,mobileImageName));
    }
    @GetMapping("/image/{id}/desktop/{desktopImageName}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable("desktopImageName") String desktopImageName, @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.downloadDesktopImage(id,desktopImageName));
    }
    @GetMapping("/image/{id}/thumbnail/{thumbnailImageName}")
    public ResponseEntity<byte[]> downloadThumbnailImage(@PathVariable("thumbnailImageName") String thumbnailImageName, @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.downloadThumbnailImage(id,thumbnailImageName));
    }

}
