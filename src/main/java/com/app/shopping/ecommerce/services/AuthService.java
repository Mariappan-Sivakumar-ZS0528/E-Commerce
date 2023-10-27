package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.AdminRegistrationDto;
import com.app.shopping.ecommerce.payload.LoginDto;

import java.util.List;


public interface AuthService {
    String login(LoginDto loginDto);
    AdminRegistrationDto registerAdmin(AdminRegistrationDto adminRegistrationDto);
    List<AdminRegistrationDto> getAllAdmins();
}
