package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
