package com.app.shopping.ecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtAuthenticationFilterTest {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    Authentication authentication;

    HttpServletRequest request;
    HttpServletResponse response;
    FilterChain filterChain;
    UserDetails userDetails;

    @BeforeEach
    void setUp() {
        request=Mockito.mock(HttpServletRequest.class);
        response=new MockHttpServletResponse();

        filterChain= Mockito.mock(FilterChain.class);
        authentication= new UsernamePasswordAuthenticationToken("email","password");
        userDetails=Mockito.mock(UserDetails.class);
        Mockito.when(jwtTokenProvider.validateToken(Mockito.any())).thenReturn(true);
        Mockito.when(userDetailsService.loadUserByUsername(Mockito.any())).thenReturn(userDetails);
        Mockito.when(jwtTokenProvider.getEmailFromJwtToken(Mockito.any())).thenReturn("email");
    }

    @Test
    void doFilterInternal() throws ServletException, IOException {
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
    }


    @Test
    void doFilterInternal_NoBearer() throws ServletException, IOException {
        Mockito.when(request.getHeader("Authorization")).thenReturn("Basic token");
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
    }

    @Test
    void doFilterInternal_Success() throws ServletException, IOException {
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer token");
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
    }

    @Test
    void doFilterInternal_InvalidToken() throws ServletException, IOException {
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer token");
        Mockito.when(jwtTokenProvider.validateToken(Mockito.any())).thenReturn(false);
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
    }

}