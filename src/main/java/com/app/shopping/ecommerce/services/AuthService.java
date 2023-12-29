package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.AdminRegistrationDto;
import com.app.shopping.ecommerce.payload.LoginDto;
import com.app.shopping.ecommerce.payload.SupplierPassword;
import org.springframework.ui.Model;

import java.util.List;


public interface AuthService {
    String login(LoginDto loginDto);
    AdminRegistrationDto registerAdmin(AdminRegistrationDto adminRegistrationDto);
    List<AdminRegistrationDto> getAllAdmins();
    String setSupplierPassword(Long id, SupplierPassword supplierPassword);

    String sendPasswordResetPin(String email);
    String processPasswordReset(String email, String pin, String newPassword);
}
