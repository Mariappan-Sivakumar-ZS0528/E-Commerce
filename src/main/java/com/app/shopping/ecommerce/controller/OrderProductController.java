package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.entity.Order;
import com.app.shopping.ecommerce.payload.CheckoutDto;
import com.app.shopping.ecommerce.services.OrderProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderProduct")
public class OrderProductController {
    private OrderProductService orderProductService;
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }
    @PostMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> checkout(HttpServletRequest request, @RequestBody CheckoutDto checkout){
        return new ResponseEntity<>(orderProductService.checkout(request,checkout), HttpStatus.OK);
    }
}
