package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.SubCategoryDto;
import com.app.shopping.ecommerce.services.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class SubCategoryController {
    private SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add Sub Category", description = "Create Sub Category")
    @ApiResponse(responseCode = "201", description = "Sub Category created successfully")
    @PostMapping("{categoryId}/sub-categories")
    public ResponseEntity<SubCategoryDto> createSubCategory(@PathVariable(value = "categoryId" ) Long categoryId,@RequestBody SubCategoryDto subCategoryDto){
        return new ResponseEntity<>(subCategoryService.createSubCategory(categoryId,subCategoryDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Get All Sub Categories", description = "Get All Sub Categories Details")
    @ApiResponse(responseCode = "200", description = "Sub Categories fetched successfully")
    @GetMapping("{categoryId}/sub-categories")
    public ResponseEntity<List<SubCategoryDto>> getSubCategoriesByCategoryId(@PathVariable(value = "categoryId" ) Long categoryId){
        return ResponseEntity.ok(subCategoryService.getSubCategoriesByCategoryId(categoryId));
    }
    @Operation(summary = "Get Sub Category", description = "Get Sub Category Details")
    @ApiResponse(responseCode = "200", description = "Sub Category fetched successfully")
    @GetMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<SubCategoryDto> getSubCategoryById(@PathVariable(value = "categoryId" ) Long categoryId,@PathVariable(value = "subCategoryId" ) Long subCategoryId){
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(categoryId,subCategoryId));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update Sub Category", description = "Update Sub Category")
    @ApiResponse(responseCode = "200", description = "Sub Category updated successfully")
    @PutMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<SubCategoryDto> updateSubCategory(@PathVariable(value = "categoryId" ) Long categoryId,@PathVariable(value = "subCategoryId" ) Long subCategoryId,@RequestBody SubCategoryDto subCategoryDto){
        return ResponseEntity.ok(subCategoryService.updateSubCategory(categoryId,subCategoryDto,subCategoryId));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete Sub Category", description = "Delete Sub Category")
    @ApiResponse(responseCode = "200", description = "Sub Category deleted successfully")
    @DeleteMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable(value = "categoryId" ) Long categoryId,@PathVariable(value = "subCategoryId" ) Long subCategoryId){
        return new ResponseEntity<>(subCategoryService.deleteSubCategory(categoryId,subCategoryId),HttpStatus.OK);
    }
}