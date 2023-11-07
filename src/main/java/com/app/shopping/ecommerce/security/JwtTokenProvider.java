package com.app.shopping.ecommerce.security;

import com.app.shopping.ecommerce.exception.ECommerceApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecrete;
    @Value("${app-jwt-expiration-millisecond}")
    private long jwtExpirationMs;

    //    generate Jwt Token
//    public String generateJwtToken(Authentication authentication) {
//
//        String email = authentication.getName();
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime() + jwtExpirationMs);
//        String token = Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(expireDate).signWith(key()).compact();
//        return token;
//    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecrete)
        );
    }

    //    Get Email Id from token
    public String getEmailFromJwtToken(String token) {
        String email = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
        return email;
    }

    //    Validate Jwt token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }

    }
    public String generateToken(Authentication authentication){
        String username=authentication.getName();
        Date currentDate=new Date();
        Date expiryDate= new Date(currentDate.getTime()+jwtExpirationMs);
        String jwtToken=Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
        return jwtToken;
    }
}
