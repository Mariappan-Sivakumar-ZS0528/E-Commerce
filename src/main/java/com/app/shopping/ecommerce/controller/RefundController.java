package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.RefundDto;
import com.app.shopping.ecommerce.services.RefundService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refund")
public class RefundController
{
    private RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }
    @GetMapping
    public ResponseEntity<List<RefundDto>> getAll(HttpServletRequest request)
    {
        List<RefundDto> refund = refundService.getBySupplier(request);
        return ResponseEntity.status(HttpStatus.OK).body(refund);
    }
    @PostMapping("/{id}/status")
    public ResponseEntity<String> statusUpdate(@PathVariable Long id, @RequestBody RefundDto refundDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(refundService.statusUpdate(id, refundDto));
    }
}
