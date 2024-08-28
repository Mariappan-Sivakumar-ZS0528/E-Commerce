package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.AdminRegistrationDto;
import com.app.shopping.ecommerce.payload.JWTAuthResponse;
import com.app.shopping.ecommerce.payload.LoginDto;
import com.app.shopping.ecommerce.payload.SupplierPassword;
import com.app.shopping.ecommerce.services.AuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @InjectMocks
    AuthController authController;
    @Mock
    AuthService authService;
    LoginDto loginDto;
    JWTAuthResponse jwtAuthResponse;
    AdminRegistrationDto adminRegistrationDto;
    List<AdminRegistrationDto> adminRegistrationDtoList;
    SupplierPassword supplierPassword;

    @BeforeEach
    void setUp() {
        loginDto = new LoginDto();
        jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setToken("token");
        adminRegistrationDto = new AdminRegistrationDto();
        supplierPassword = new SupplierPassword();
        adminRegistrationDtoList = List.of(adminRegistrationDto);
        Mockito.when(authService.login(loginDto)).thenReturn("token");
        Mockito.when(authService.registerAdmin(adminRegistrationDto)).thenReturn(adminRegistrationDto);
        Mockito.when(authService.getAllAdmins()).thenReturn(adminRegistrationDtoList);
        Mockito.when(authService.setSupplierPassword(1L, supplierPassword)).thenReturn("Password changed successfully");
    }

    @Test
    void login() {
        ResponseEntity<JWTAuthResponse> responseEntity = authController.login(loginDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtAuthResponse.getToken(), responseEntity.getBody().getToken());
    }

    @Test
    void registerAdmin() {
        ResponseEntity<AdminRegistrationDto> responseEntity = authController.registerAdmin(adminRegistrationDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(adminRegistrationDto, responseEntity.getBody());
    }

    @Test
    void getAllAdmins() {
        ResponseEntity<List<AdminRegistrationDto>> responseEntity = authController.getAllAdmins();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(adminRegistrationDtoList, responseEntity.getBody());
    }

    @Test
    void updateSupplierPassword() {
        ResponseEntity<String> responseEntity = authController.updateSupplierPassword(1L, supplierPassword);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Password changed successfully", responseEntity.getBody());
    }
}