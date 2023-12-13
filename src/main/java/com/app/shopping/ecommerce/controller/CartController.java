package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CartDto;
import com.app.shopping.ecommerce.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(@RequestBody CartDto cartDto, HttpServletRequest request) {
        return new ResponseEntity<>(cartService.addToCart(cartDto, request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCart(HttpServletRequest request) {
        return ResponseEntity.ok(cartService.getAllCart(request));
    }
}
