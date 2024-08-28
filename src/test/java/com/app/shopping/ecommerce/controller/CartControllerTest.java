package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.CartDto;
import com.app.shopping.ecommerce.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CartControllerTest {

    @Mock
    CartService cartService;

    @InjectMocks
    CartController cartController;
    CartDto cartDto;
    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);

    @BeforeEach
    void setUp() {
        cartDto=new CartDto();
        Mockito.when(cartService.addToCart(cartDto, request)).thenReturn(cartDto);
        Mockito.when(cartService.getCartById(1L, request)).thenReturn(cartDto);
        Mockito.when(cartService.getAllCart(request)).thenReturn(List.of(cartDto));
        Mockito.when(cartService.updateCart(1L, cartDto, request)).thenReturn(cartDto);
//        Mockito.when(cartService.deleteCart(1L, request)).thenReturn("Cart deleted successfully");
    }

    @WithMockUser(roles = "USER")
    @Test
    void addToCart() {
        ResponseEntity<CartDto> responseEntity = cartController.addToCart(cartDto, request);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(cartDto, responseEntity.getBody());
    }

    @WithMockUser(roles = "USER")
    @Test
    void getAllCart() {
        ResponseEntity<List<CartDto>> responseEntity = cartController.getAllCart(request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(cartDto), responseEntity.getBody());
    }

    @WithMockUser(roles = "USER")
    @Test
    void getCartById() {
        ResponseEntity<CartDto> responseEntity = cartController.getCartById(1L, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cartDto, responseEntity.getBody());
    }

    @WithMockUser(roles = "USER")
    @Test
    void updateCart() {
        ResponseEntity<CartDto> responseEntity = cartController.updateCart(1L, cartDto, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cartDto, responseEntity.getBody());
    }

    @WithMockUser(roles = "USER")
    @Test
    void deleteCart() {
        ResponseEntity<String> responseEntity = cartController.deleteCart(1L, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Cart deleted successfully", responseEntity.getBody());
    }
}