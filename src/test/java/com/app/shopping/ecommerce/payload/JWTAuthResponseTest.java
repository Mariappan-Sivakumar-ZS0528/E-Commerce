package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTAuthResponseTest {
    JWTAuthResponse jwtAuthResponse;
    @BeforeEach
    void setUp() {
        jwtAuthResponse = new JWTAuthResponse("1","Bearer");
    }

    @Test
    void getToken() {
        assertEquals("1",jwtAuthResponse.getToken());
    }

    @Test
    void getTokenType() {
        assertEquals("Bearer",jwtAuthResponse.getTokenType());
    }

    @Test
    void setToken() {
        jwtAuthResponse.setToken("2");
        assertEquals("2",jwtAuthResponse.getToken());
    }

    @Test
    void setTokenType() {
        jwtAuthResponse.setTokenType("Bearer");
        assertEquals("Bearer",jwtAuthResponse.getTokenType());
    }
    @Test
    void testForNOargs() {
        JWTAuthResponse jwtAuthResponse1 = new JWTAuthResponse();
        assertNotNull(jwtAuthResponse1);
    }
}