package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FaqDto;
import com.app.shopping.ecommerce.services.FaqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
@Tag(name = "Faq Controller", description = "Crud operation related to faq")
public class FaqController {
    private FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @Operation(summary = "Add new frequently asked question")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<FaqDto> addFaq(@RequestBody FaqDto newFaq) {
        return ResponseEntity.ok(faqService.addFaq(newFaq));
    }
    @Operation(summary = "Get all frequently asked questions")
    @GetMapping
    public ResponseEntity<List<FaqDto>> getAllFaq() {
        return ResponseEntity.ok(faqService.getAllFaq());
    }
    @Operation(summary = "Get frequently asked question by id")
    @GetMapping("/{id}")
    public ResponseEntity<FaqDto> getFaqById(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getFaqById(id));
    }
    @Operation(summary = "Update frequently asked question")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FaqDto> updateFaq(@PathVariable Long id, @RequestBody FaqDto updatedFaq) {
        return ResponseEntity.ok(faqService.updateFaq(id, updatedFaq));
    }
    @Operation(summary = "Delete frequently asked question")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFaq(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.deleteFaq(id));
    }
}