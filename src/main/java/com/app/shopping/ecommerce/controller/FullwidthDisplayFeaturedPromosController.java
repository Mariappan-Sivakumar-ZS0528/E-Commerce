package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/FullWidthDisplay")
@Tag(name = "FullWidthDisplay", description = "CRUD operations for FullWidthDisplay")
public class FullwidthDisplayFeaturedPromosController {
    private FullwidthDisplayFeaturedPromosService fullwidthDisplayFeaturedPromosService;
    public FullwidthDisplayFeaturedPromosController(FullwidthDisplayFeaturedPromosService fullwidthDisplayFeaturedPromosService){
        this.fullwidthDisplayFeaturedPromosService=fullwidthDisplayFeaturedPromosService;
    }
    @Operation(summary = "Get all FullWidthDisplayPromos")
    @GetMapping("/getFullWidthDisplayPromos")
    public ResponseEntity<List<FullwidthDisplayFeaturedPromosDto>> getpromos(){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.getall(),HttpStatus.OK );
    }
    @Operation(summary = "Add FullWidthDisplayPromos")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addFullWidthDisplayPromos")
    public ResponseEntity<FullwidthDisplayFeaturedPromosDto> addPromos(@RequestBody FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.add(fullwidthDisplayFeaturedPromosDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Upload Images for FullWidthDisplayPromos in specific id")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/uploadImages/{id}")
    public ResponseEntity<String> uploadImages(@PathVariable Long id,@RequestParam("mobileImage") MultipartFile mobileImage , @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException{
        byte[] mobileImageBytes=mobileImage.getBytes();
        byte[] desktopImageBytes=desktopImage.getBytes();
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.uploadImages(id,mobileImageBytes,desktopImageBytes),HttpStatus.OK);
    }
    @Operation(summary = "Download Mobile Image for specific id")
    @GetMapping("/downloadMobileImage/{id}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.downloadMobileImage(id),HttpStatus.OK);
    }
    @Operation(summary = "Get measurement for specific id and also by WhichImageMeasurements")
    @GetMapping("/getmeasurement/{id}/{WhichImageMeasurements}")
    public ResponseEntity<String> getMeasurement(@PathVariable Long id,@PathVariable String WhichImageMeasurements){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.getMeasurement(id,WhichImageMeasurements),HttpStatus.OK);
    }
    @Operation(summary = "Delete FullWidthDisplayPromos by id")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("deletebyId/{id}")
    public ResponseEntity<String> deletePromos(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.deletePromos(id),HttpStatus.OK);
    }
    @Operation(summary = "Download Desktop Image for specific id")
    @GetMapping("/downloadDesktopImage/{id}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.downloadDesktopImage(id),HttpStatus.OK);
    }
    @Operation (summary = "Disable FullWidthDisplayPromos by id")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/disablePromos/{id}")
    public ResponseEntity<String> disablePromos(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.disablePromos(id),HttpStatus.OK);
    }
    @Operation (summary = "Enable FullWidthDisplayPromos by id")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/enablePromos/{id}")
    public ResponseEntity<String> enablePromos(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.enablePromos(id),HttpStatus.OK);
    }
    @Operation (summary = "Update Whole FullWidthDisplayPromos")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateWholePromos/{id}")
    public ResponseEntity<FullwidthDisplayFeaturedPromosDto> updateWholePromos(@PathVariable Long id,@RequestBody FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.updateWholePromos(id,fullwidthDisplayFeaturedPromosDto),HttpStatus.OK);
    }
}
