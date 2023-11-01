package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.RefundDto;
import com.app.shopping.ecommerce.services.RefundService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/refund")
public class RefundController
{
    private RefundService refundService;
    @GetMapping
    public ResponseEntity<List<RefundDto>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(refundService.getAllByMerchantId());
    }
}
