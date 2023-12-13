package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.CartDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CartService {
    CartDto addToCart(CartDto cartDto, HttpServletRequest request);
    List<CartDto> getAllCart(HttpServletRequest request);
}
