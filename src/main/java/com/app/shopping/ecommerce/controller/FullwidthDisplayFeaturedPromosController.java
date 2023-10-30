package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/FullWidthDisplay")
public class FullwidthDisplayFeaturedPromosController {
    private FullwidthDisplayFeaturedPromosService fullwidthDisplayFeaturedPromosService;
    public FullwidthDisplayFeaturedPromosController(FullwidthDisplayFeaturedPromosService fullwidthDisplayFeaturedPromosService){
        this.fullwidthDisplayFeaturedPromosService=fullwidthDisplayFeaturedPromosService;
    }
    @GetMapping("/getFullWidthDisplayPromos")
    public ResponseEntity<List<FullwidthDisplayFeaturedPromosDto>> getpromos(){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.getall(),HttpStatus.OK );
    }
    @PostMapping("/addFullWidthDisplayPromos")
    public ResponseEntity<FullwidthDisplayFeaturedPromosDto> addPromos(@RequestBody FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.add(fullwidthDisplayFeaturedPromosDto), HttpStatus.CREATED);
    }
    @PostMapping("/uploadImages/{id}")
    public ResponseEntity<String> uploadImages(@PathVariable Long id,@RequestParam("mobileImage") MultipartFile mobileImage , @RequestParam("desktopImage") MultipartFile desktopImage) throws IOException{
        byte[] mobileImageBytes=mobileImage.getBytes();
        byte[] desktopImageBytes=desktopImage.getBytes();
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.uploadImages(id,mobileImageBytes,desktopImageBytes),HttpStatus.OK);
    }
    @GetMapping("/downloadMobileImage/{id}")
    public ResponseEntity<byte[]> downloadMobileImage(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.downloadMobileImage(id),HttpStatus.OK);
    }
    @GetMapping("/getmeasurement/{id}/{WhichImageMeasurements}")
    public ResponseEntity<String> getMeasurement(@PathVariable Long id,@PathVariable String WhichImageMeasurements){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.getMeasurement(id,WhichImageMeasurements),HttpStatus.OK);
    }
    @DeleteMapping("deletebyId/{id}")
    public ResponseEntity<String> deletePromos(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.deletePromos(id),HttpStatus.OK);
    }
    @GetMapping("/downloadDesktopImage/{id}")
    public ResponseEntity<byte[]> downloadDesktopImage(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.downloadDesktopImage(id),HttpStatus.OK);
    }
    @PostMapping("/disablePromos/{id}")
    public ResponseEntity<String> disablePromos(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.disablePromos(id),HttpStatus.OK);
    }
    @PostMapping("/enablePromos/{id}")
    public ResponseEntity<String> enablePromos(@PathVariable Long id){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.enablePromos(id),HttpStatus.OK);
    }
    @PutMapping("/updateWholePromos/{id}")
    public ResponseEntity<FullwidthDisplayFeaturedPromosDto> updateWholePromos(@PathVariable Long id,@RequestBody FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto){
        return new ResponseEntity<>(fullwidthDisplayFeaturedPromosService.updateWholePromos(id,fullwidthDisplayFeaturedPromosDto),HttpStatus.OK);
    }
}
