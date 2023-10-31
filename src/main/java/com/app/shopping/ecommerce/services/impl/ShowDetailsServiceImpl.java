package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.payload.ShowDetailsDto;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.services.ShowDetailsService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ShowDetailsServiceImpl implements ShowDetailsService {
//    @Value("${app.jwt-secret}")
//    private String jwtSecrete;
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private EmailExtractor emailExtractor;

    public ShowDetailsServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, EmailExtractor emailExtractor) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.emailExtractor = emailExtractor;
    }

    @Override
    public ShowDetailsDto getShowDetails(HttpServletRequest request) {
        String email = emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(() -> new ECommerceApiException(HttpStatus.BAD_REQUEST, "Invalid Authentication"));
        ShowDetailsDto newShowDetails=new ShowDetailsDto();
        newShowDetails.setId(supplier.getId());
        newShowDetails.setName(supplier.getCompany());
        newShowDetails.setEmail(supplier.getEmail());
        newShowDetails.setAddress(supplier.getAddressLine1()+" "+supplier.getAddressLine2()+" "+supplier.getAddressLine3());
        return newShowDetails;
    }

//    private String getEmailFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (!(bearerToken != null && bearerToken.startsWith("Bearer "))) {
//            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Invalid token");
//        }
//        String token = bearerToken.substring(7);
//        String email= Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
//        return email;
//    }
//    private Key key() {
//        return Keys.hmacShaKeyFor(
//                Decoders.BASE64.decode(jwtSecrete)
//        );
//    }
}
