package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.entity.Legal;
import com.app.shopping.ecommerce.payload.LegalDto;
import com.app.shopping.ecommerce.services.LegalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/legal")
@Tag(name = "Legal Controller", description = "Crud operation related to legal")
public class LegalController {
    private LegalService legalService;

    public LegalController(LegalService legalService) {
        this.legalService = legalService;
    }
    @Operation(summary = "Add new legal")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<LegalDto> addLegal(@RequestBody LegalDto newLegal) {
    return ResponseEntity.ok(legalService.addLegal(newLegal));
    }
    @GetMapping
    @Operation(summary = "Get all legal")
    public ResponseEntity<List<Legal>> getAllLegal() {
        return ResponseEntity.ok(legalService.getAllLegal());
    }
    @Operation(summary = "Get legal by id")
    @GetMapping("/{id}")
    public ResponseEntity<LegalDto> getLegalById(@PathVariable Long id) {
        return ResponseEntity.ok(legalService.getLegalById(id));
    }
    @Operation(summary = "Update legal")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LegalDto> updateLegal(@PathVariable Long id, @RequestBody LegalDto updatedLegal) {
        return ResponseEntity.ok(legalService.updateLegal(id, updatedLegal));
    }
    @Operation(summary = "Delete legal")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLegal(@PathVariable Long id) {
        return ResponseEntity.ok(legalService.deleteLegal(id));
    }

}
