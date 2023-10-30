package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.DisplayInTilesPromosDto;
import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.services.DisplayInTilesPromosService;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/DisplayInTiles")
public class DisplayInTilesPromosController {
    private DisplayInTilesPromosService displayInTilesPromosService;
    public DisplayInTilesPromosController(DisplayInTilesPromosService displayInTilesPromosService){
        this.displayInTilesPromosService=displayInTilesPromosService;
    }
    @GetMapping("/getDisplayInTilesPromos")
    public ResponseEntity<List<DisplayInTilesPromosDto>> getpromos(){
        return new ResponseEntity<>(displayInTilesPromosService.getall(), HttpStatus.OK );
    }
    @PostMapping("/addDisplayInTilesPromos")
    public ResponseEntity<DisplayInTilesPromosDto> addPromos(@RequestBody DisplayInTilesPromosDto displayInTilesPromosDto){
        return new ResponseEntity<>(displayInTilesPromosService.add(displayInTilesPromosDto), HttpStatus.CREATED);
    }
    @PostMapping("/uploadImages/{id}")
    public ResponseEntity<String> uploadImages(@PathVariable Long id, @RequestParam("mobileImage") MultipartFile mobileImage , @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException {
        byte[] mobileImageBytes=mobileImage.getBytes();
        byte[] desktopImageBytes=desktopImage.getBytes();
        return new ResponseEntity<>(displayInTilesPromosService.uploadImages(id,mobileImageBytes,desktopImageBytes),HttpStatus.OK);
    }
    @GetMapping("/downloadMobileImage/{id}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.downloadMobileImage(id),HttpStatus.OK);
    }
    @GetMapping("/getmeasurement/{id}/{WhichImageMeasurements}")
    public ResponseEntity<String> getMeasurement(@PathVariable Long id,@PathVariable String WhichImageMeasurements){
        return new ResponseEntity<>(displayInTilesPromosService.getMeasurement(id,WhichImageMeasurements),HttpStatus.OK);
    }
    @DeleteMapping("deletebyId/{id}")
    public ResponseEntity<String> deletePromos(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.deletePromos(id),HttpStatus.OK);
    }
    @GetMapping("/downloadDesktopImage/{id}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.downloadDesktopImage(id),HttpStatus.OK);
    }
    @PostMapping("/disablePromos/{id}")
    public ResponseEntity<String> disablePromos(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.disablePromos(id),HttpStatus.OK);
    }
    @PostMapping("/enablePromos/{id}")
    public ResponseEntity<String> enablePromos(@PathVariable Long id){
        return new ResponseEntity<>(displayInTilesPromosService.enablePromos(id),HttpStatus.OK);
    }
    @PutMapping("/updateWholePromos/{id}")
    public ResponseEntity<DisplayInTilesPromosDto> updateWholePromos(@PathVariable Long id,@RequestBody DisplayInTilesPromosDto displayInTilesPromosDto){
        return new ResponseEntity<>(displayInTilesPromosService.updateWholePromos(id,displayInTilesPromosDto),HttpStatus.OK);
    }
}
