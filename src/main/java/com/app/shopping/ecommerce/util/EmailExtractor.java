package com.app.shopping.ecommerce.util;

import com.app.shopping.ecommerce.exception.ECommerceApiException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
@Component
public class EmailExtractor {
    Logger logger= LoggerFactory.getLogger(EmailExtractor.class);

    public String getEmailFromRequest(HttpServletRequest request) {
        logger.info(request.getHeader("Authorization"));
        String bearerToken = request.getHeader("Authorization");
        if (!(bearerToken != null && bearerToken.startsWith("Bearer "))) {
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
        String token = bearerToken.substring(7);
        String email= Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
        return email;
    }
    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecrete)
        );
    }
    @Value("${app.jwt-secret}")
    private String jwtSecrete;
}
