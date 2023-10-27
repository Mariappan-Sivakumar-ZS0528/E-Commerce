package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.AdminRegistrationDto;
import com.app.shopping.ecommerce.payload.JWTAuthResponse;
import com.app.shopping.ecommerce.payload.LoginDto;
import com.app.shopping.ecommerce.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    Build Login Rest Api
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
    @PostMapping("/admin/register")
    public ResponseEntity<AdminRegistrationDto> registerAdmin(@RequestBody AdminRegistrationDto adminRegistrationDto){
        return ResponseEntity.ok(authService.registerAdmin(adminRegistrationDto));
    }
    @GetMapping("/admin/all")
    public ResponseEntity<List<AdminRegistrationDto>> getAllAdmins(){
        return ResponseEntity.ok(authService.getAllAdmins());
    }
}
