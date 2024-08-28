package com.app.shopping.ecommerce.security;

import com.app.shopping.ecommerce.exception.ECommerceApiException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Value("${app.jwt-secret}")
    private String jwtSecrete;
    @Value("${app-jwt-expiration-millisecond}")
    private long jwtExpirationMs;

    @MockBean
    Authentication authentication;
    String token;

    @BeforeEach
    void setUp() {
        Mockito.when(authentication.getName()).thenReturn("email");

        token = jwtTokenProvider.generateToken(authentication);
    }

    @Test
    void getEmailFromJwtToken() {
        assertEquals("email", jwtTokenProvider.getEmailFromJwtToken(token));
    }

    @Test
    void getEmailFromJwtToken_MalformedJwtException() {
        MalformedJwtException exception = assertThrows(MalformedJwtException.class, () -> jwtTokenProvider.getEmailFromJwtToken("malformed token"));
    }

    @Test
    void validateToken_Success() {
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void validateToken_MalformedJwtException() {
        ECommerceApiException exception = assertThrows(ECommerceApiException.class, () -> jwtTokenProvider.validateToken("malformed token"));
        assertEquals("Invalid JWT token", exception.getMessage());
    }

    @Test
    void validateToken_ExpiredJwtException() {
        ECommerceApiException exception = assertThrows(ECommerceApiException.class, () -> jwtTokenProvider.validateToken(getExpiredToken("email")));
        assertEquals("Expired JWT token", exception.getMessage());
    }
    @Test
    void validateToken_UnsupportedJwtException() {
        String unsupportedToken=Jwts.builder().setPayload("payload").signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecrete))).compact();
        ECommerceApiException exception = assertThrows(ECommerceApiException.class, () -> jwtTokenProvider.validateToken(unsupportedToken));
        assertEquals("Unsupported JWT token", exception.getMessage());
    }
    @Test
    public void testValidateToken_EmptyClaims() {
        String token = "";
        ECommerceApiException exception = assertThrows(ECommerceApiException.class, () -> {
            jwtTokenProvider.validateToken(token);
        });
        assertEquals("JWT claims string is empty.", exception.getMessage());
    }
    @Test
    void generateToken() {
        String token = jwtTokenProvider.generateToken(authentication);
        assertNotNull(token);
        assertEquals("email", jwtTokenProvider.getEmailFromJwtToken(token));
        assertTrue(jwtTokenProvider.validateToken(token));
    }



    private String getExpiredToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() - 1000);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(
                                Decoders.BASE64.decode(jwtSecrete)))
                .compact();
    }

}