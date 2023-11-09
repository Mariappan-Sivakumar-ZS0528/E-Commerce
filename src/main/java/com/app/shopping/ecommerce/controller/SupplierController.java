package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ShowDetailsDto;
import com.app.shopping.ecommerce.payload.SupplierContactDto;
import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierReg;
import com.app.shopping.ecommerce.services.ShowDetailsService;
import com.app.shopping.ecommerce.services.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@Tag(name = "Supplier Controller", description = "Crud operation related to supplier")
public class SupplierController {
    private SupplierService supplierService;
    private ShowDetailsService showDetailsService;

    public SupplierController(SupplierService supplierService, ShowDetailsService showDetailsService) {
        this.supplierService = supplierService;
        this.showDetailsService = showDetailsService;
    }

    @Operation(summary = "Add new supplier")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<SupplierReg> addSupplier(@RequestBody SupplierReg newSupplier) {
        return new ResponseEntity<>(supplierService.RegistrationSupplier(newSupplier), HttpStatus.CREATED);

    }

    @Operation(summary = "Get all suppliers")
    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @Operation(summary = "Get supplier by id")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @Operation(summary = "Update supplier")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto updatedSupplier) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, updatedSupplier));
    }

    @Operation(summary = "Delete supplier")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }

    @GetMapping("api/getDetail")
    @Operation(summary = "Get supplier details")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<ShowDetailsDto> getSupplierDetails(HttpServletRequest request) {
        return ResponseEntity.ok(showDetailsService.getShowDetails(request));
    }
    @Operation(summary = "Update supplier contact person")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @PostMapping("api/contact-details")
    public ResponseEntity<SupplierContactDto> updateSupplierContact(@PathVariable Long id, @RequestBody SupplierContactDto updatedSupplierContact) {
        return ResponseEntity.ok(supplierService.UpdateSupplierContact(id, updatedSupplierContact));
    }

    @GetMapping("api/getContact-person")
    @Operation(summary = "Get supplier contact person")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<SupplierContactDto> getSupplierContactById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierContactById(id));
    }


}