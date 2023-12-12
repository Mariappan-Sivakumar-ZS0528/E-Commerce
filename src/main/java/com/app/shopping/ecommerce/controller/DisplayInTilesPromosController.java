package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.DisplayInTilesPromosDto;
import com.app.shopping.ecommerce.services.DisplayInTilesPromosService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/DisplayInTiles")
@Tag(name = "DisplayInTiles", description = "CRUD operations for DisplayInTiles")
public class DisplayInTilesPromosController {
    private DisplayInTilesPromosService displayInTilesPromosService;
    public DisplayInTilesPromosController(DisplayInTilesPromosService displayInTilesPromosService){
        this.displayInTilesPromosService=displayInTilesPromosService;
    }
    @Operation(summary = "Get all DisplayInTilesPromos")
    @GetMapping("/getDisplayInTilesPromos")
    public ResponseEntity<List<DisplayInTilesPromosDto>> getpromos(){
        return new ResponseEntity<>(displayInTilesPromosService.getall(), HttpStatus.OK );
    }
    @Operation(summary = "Add DisplayInTilesPromos")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addDisplayInTilesPromos")
    public ResponseEntity<DisplayInTilesPromosDto> addPromos(@RequestBody DisplayInTilesPromosDto displayInTilesPromosDto){
        return new ResponseEntity<>(displayInTilesPromosService.add(displayInTilesPromosDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Create a new resource")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/uploadImages/{id}")
    public ResponseEntity<String> uploadImages(@PathVariable Long id,
    @RequestParam("mobileImage") MultipartFile mobileImage ,
    @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException {
        byte[] mobileImageBytes=mobileImage.getBytes();
        byte[] desktopImageBytes=desktopImage.getBytes();
        return new ResponseEntity<>(displayInTilesPromosService.uploadImages(id,mobileImageBytes,desktopImageBytes),HttpStatus.OK);
    }
    @Operation(summary = "Download Mobile Image for specific id")
    @GetMapping("/downloadMobileImage/{id}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.downloadMobileImage(id),HttpStatus.OK);
    }
    @Operation(summary = "Get measurement for specific id and also by WhichImageMeasurements")
    @GetMapping("/getmeasurement/{id}/{WhichImageMeasurements}")
    public ResponseEntity<String> getMeasurement(@PathVariable Long id,@PathVariable String WhichImageMeasurements){
        return new ResponseEntity<>(displayInTilesPromosService.getMeasurement(id,WhichImageMeasurements),HttpStatus.OK);
    }
    @Operation(summary = "Delete DisplayInTilesPromos by id")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("deletebyId/{id}")
    public ResponseEntity<String> deletePromos(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.deletePromos(id),HttpStatus.OK);
    }
    @Operation(summary = "Download Desktop Image by id")
    @GetMapping("/downloadDesktopImage/{id}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.downloadDesktopImage(id),HttpStatus.OK);
    }
    @Operation(summary = "Disable DisplayInTilesPromos by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/disablePromos/{id}")
    public ResponseEntity<String> disablePromos(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.disablePromos(id),HttpStatus.OK);
    }
    @Operation(summary = "Enable DisplayInTilesPromos by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/enablePromos/{id}")
    public ResponseEntity<String> enablePromos(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.enablePromos(id),HttpStatus.OK);
    }
    @Operation(summary = "Update DisplayInTilesPromos by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateWholePromos/{id}")
    public ResponseEntity<DisplayInTilesPromosDto> updateWholePromos(@PathVariable Long id,@RequestBody DisplayInTilesPromosDto displayInTilesPromosDto){
        return new ResponseEntity<>(displayInTilesPromosService.updateWholePromos(id,displayInTilesPromosDto),HttpStatus.OK);
    }
}
