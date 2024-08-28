package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CheckoutDto;
import com.app.shopping.ecommerce.services.OrderProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderProductControllerTest {
    @Mock
    private OrderProductService orderProductService;
    @InjectMocks
    private OrderProductController orderProductController;
    @Test
    void checkout() {
        CheckoutDto checkout = new CheckoutDto();
        HttpServletRequest request = new MockHttpServletRequest();
        when(orderProductService.checkout(request,checkout)).thenReturn("success");
        String actual = orderProductController.checkout(request,checkout).getBody();
        assertEquals("success", actual);
    }
}