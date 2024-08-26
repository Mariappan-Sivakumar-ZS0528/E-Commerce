package com.app.shopping.ecommerce.util;

import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailExtractorTest {
    @Autowired
    EmailExtractor emailExtractor;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    Authentication authentication;
    HttpServletRequest request;
    String token;

    @BeforeEach
    void setUp() {
        authentication = Mockito.mock(Authentication.class);
        request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(authentication.getName()).thenReturn("email");
        token = jwtTokenProvider.generateToken(authentication);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    }

    @Test
    void getEmailFromRequest() {
        assertEquals("email", emailExtractor.getEmailFromRequest(request));
    }

    @Test
    void getEmailFromRequest_TokenNotPresent() {
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);
        ECommerceApiException exception = assertThrows(ECommerceApiException.class, () -> emailExtractor.getEmailFromRequest(request));
        assertEquals("Invalid token", exception.getMessage());
    }

    @Test
    void getEmailFromRequest_BearerTokenNotPresent(){
        Mockito.when(request.getHeader("Authorization")).thenReturn("Basic token");
        ECommerceApiException exception = assertThrows(ECommerceApiException.class, () -> emailExtractor.getEmailFromRequest(request));
        assertEquals("Invalid token", exception.getMessage());
    }
}