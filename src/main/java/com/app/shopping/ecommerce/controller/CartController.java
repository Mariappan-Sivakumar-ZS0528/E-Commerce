package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CartDto;
import com.app.shopping.ecommerce.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(cartService.addToCart(cartDto, request));
    }
}
