package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.entity.Order;
import com.app.shopping.ecommerce.entity.OrderProduct;
import com.app.shopping.ecommerce.payload.CheckoutDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface OrderProductService {
    String checkout(HttpServletRequest request, CheckoutDto checkout); // <>
}
