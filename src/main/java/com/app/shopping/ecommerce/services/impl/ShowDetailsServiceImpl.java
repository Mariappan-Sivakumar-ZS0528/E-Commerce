package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.ShowDetailsDto;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.services.ShowDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;
@Service
public class ShowDetailsServiceImpl implements ShowDetailsService {
    @Value("${app.jwt-secret}")
    private String jwtSecrete;
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;

    public ShowDetailsServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShowDetailsDto getShowDetails(HttpServletRequest request) {
        String email = getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(() -> new ECommerceApiException(HttpStatus.BAD_REQUEST, "Invalid Authentication"));
        return modelMapper.map(supplier, ShowDetailsDto.class);
    }

    private String getEmailFromRequest(HttpServletRequest request) {
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
}
