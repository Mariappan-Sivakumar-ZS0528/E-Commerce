package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.SubCategoryDto;
import com.app.shopping.ecommerce.services.SubCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class SubCategoryController {
    private SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }
    @PostMapping("{categoryId}/sub-categories")
    public ResponseEntity<SubCategoryDto> createSubCategory(@PathVariable(value = "categoryId" ) Long categoryId,@RequestBody SubCategoryDto subCategoryDto){
        return new ResponseEntity<>(subCategoryService.createSubCategory(categoryId,subCategoryDto), HttpStatus.CREATED);
    }
    @GetMapping("{categoryId}/sub-categories")
    public ResponseEntity<List<SubCategoryDto>> getSubCategoriesByCategoryId(@PathVariable(value = "categoryId" ) Long categoryId){
        return ResponseEntity.ok(subCategoryService.getSubCategoriesByCategoryId(categoryId));
    }
    @GetMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<SubCategoryDto> getSubCategoryById(@PathVariable(value = "categoryId" ) Long categoryId,@PathVariable(value = "subCategoryId" ) Long subCategoryId){
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(categoryId,subCategoryId));
    }
    @PutMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<SubCategoryDto> updateSubCategory(@PathVariable(value = "categoryId" ) Long categoryId,@PathVariable(value = "subCategoryId" ) Long subCategoryId,@RequestBody SubCategoryDto subCategoryDto){
        return ResponseEntity.ok(subCategoryService.updateSubCategory(categoryId,subCategoryDto,subCategoryId));
    }
    @DeleteMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable(value = "categoryId" ) Long categoryId,@PathVariable(value = "subCategoryId" ) Long subCategoryId){
        subCategoryService.deleteSubCategory(categoryId,subCategoryId);
        return new ResponseEntity<>("SubCategory deleted successfully",HttpStatus.OK);
    }
}
