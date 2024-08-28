package com.app.shopping.ecommerce.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JwtAuthenticationEntryPointTest {

    @Test
    void commence() throws ServletException, IOException {
        HttpServletRequest request=new MockHttpServletRequest();
        HttpServletResponse response=new MockHttpServletResponse();
        AuthenticationException authException= Mockito.mock(AuthenticationException.class);
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint=new JwtAuthenticationEntryPoint();
        jwtAuthenticationEntryPoint.commence(request, response, authException);
        assertEquals(401, response.getStatus());
    }
}