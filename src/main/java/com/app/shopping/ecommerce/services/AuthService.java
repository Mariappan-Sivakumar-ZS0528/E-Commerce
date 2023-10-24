package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.LoginDto;


public interface AuthService {
    String login(LoginDto loginDto);
}
