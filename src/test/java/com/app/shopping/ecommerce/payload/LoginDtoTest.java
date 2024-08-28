package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDtoTest {
    LoginDto loginDto;
    @BeforeEach
    void setUp() {
        loginDto = new LoginDto("email","password");
    }

    @Test
    void getEmail() {
        assertEquals("email",loginDto.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("password",loginDto.getPassword());
    }

    @Test
    void setEmail() {
        loginDto.setEmail("email");
        assertEquals("email",loginDto.getEmail());
    }

    @Test
    void setPassword() {
        loginDto.setPassword("password");
        assertEquals("password",loginDto.getPassword());
    }
    @Test
    void testtoNoArgs(){
        LoginDto loginDto = new LoginDto();
        assertNotNull(loginDto);
    }
}