package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FaqDto;
import com.app.shopping.ecommerce.services.FaqService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
public class FaqController {
    private FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }


    @PostMapping
    public ResponseEntity<FaqDto> addFaq(@RequestBody FaqDto newFaq) {
        return ResponseEntity.ok(faqService.addFaq(newFaq));
    }

    @GetMapping
    public ResponseEntity<List<FaqDto>> getAllFaq() {
        return ResponseEntity.ok(faqService.getAllFaq());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FaqDto> getFaqById(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getFaqById(id));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FaqDto> updateFaq(@PathVariable Long id, @RequestBody FaqDto updatedFaq) {
        return ResponseEntity.ok(faqService.updateFaq(id, updatedFaq));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFaq(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.deleteFaq(id));
    }
}