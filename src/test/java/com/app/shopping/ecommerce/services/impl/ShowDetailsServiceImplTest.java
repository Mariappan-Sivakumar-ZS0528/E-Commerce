package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.payload.ShowDetailsDto;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.util.EmailExtractor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ShowDetailsServiceImplTest {
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private EmailExtractor emailExtractor;

    @InjectMocks
    private ShowDetailsServiceImpl showDetailsServiceImpl;
    MockHttpServletRequest request = new MockHttpServletRequest();


    @Test
    void getShowDetails() {
        when(emailExtractor.getEmailFromRequest(request)).thenReturn("abc@gmail.com");
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setCompany("abc");
        supplier.setEmail("abc@gmail.com");
        supplier.setAddressLine1("abc");
        supplier.setAddressLine2("abc");
        supplier.setAddressLine3("abc");
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        ShowDetailsDto showDetailsDto = new ShowDetailsDto();
        showDetailsDto.setId(supplier.getId());
        showDetailsDto.setName(supplier.getCompany());
        showDetailsDto.setEmail(supplier.getEmail());
        showDetailsDto.setAddress(supplier.getAddressLine1() + " " + supplier.getAddressLine2() + " " + supplier.getAddressLine3());
        assertEquals(showDetailsDto.getAddress(), showDetailsServiceImpl.getShowDetails(request).getAddress());
    }
}