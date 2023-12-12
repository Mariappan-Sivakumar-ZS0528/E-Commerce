package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierReg;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.services.SupplierService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SupplierServiceImplTest {
    @Autowired
    private SupplierService supplierService;

    @MockBean
    SupplierRepository supplierRepository;

    Supplier supplier;
    @BeforeEach
    void setUp() {
        supplier=new Supplier();
        supplier.setId(1L);
        supplier.setCompany("ABC");
        supplier.setSupplierName("supplierName");
        supplier.setAddressLine1("123");
        supplier.setAddressLine2("Abc testing");
        supplier.setCity("Tuticorin");
        supplier.setContactNumber("1234567890");
        supplier.setEmail("abc@gmail.com");
        supplier.setProfitSharingPercentage(10);
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);
        Mockito.when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        Mockito.when(supplierRepository.findAll()).thenReturn(List.of(supplier));
        Mockito.when(supplierRepository.existsById(1L)).thenReturn(true);
    }

    @Test
    void registrationSupplier() {
        SupplierReg supplierReg = new SupplierReg();
        supplierReg.setCompany("ABC");
        supplierReg.setAddressLine1("123");
        supplierReg.setAddressLine2("Abc testing");
        supplierReg.setCity("Tuticorin");
        supplierReg.setContactNumber("1234567890");
        supplierReg.setEmail("abc@gmail.com");
        supplierReg.setProfitSharingPercentage(10);
        SupplierReg supplierReg1 = supplierService.registrationSupplier(supplierReg);
        Assertions.assertEquals("ABC", supplierReg1.getCompany());
        Assertions.assertEquals("123", supplierReg1.getAddressLine1());
        Assertions.assertEquals("Abc testing", supplierReg1.getAddressLine2());
        Assertions.assertEquals("Tuticorin", supplierReg1.getCity());
        Assertions.assertEquals("1234567890", supplierReg1.getContactNumber());
        Assertions.assertEquals("abc@gmail.com", supplierReg1.getEmail());
        Assertions.assertEquals(10, supplierReg1.getProfitSharingPercentage());
    }

    @Test
    void getSupplierById() {
        SupplierDto supplierDto = supplierService.getSupplierById(1L);
        assertEquals("ABC", supplierDto.getCompany());
        assertEquals("supplierName", supplierDto.getSupplierName());
        assertEquals("123", supplierDto.getAddressLine1());
        assertEquals("Abc testing", supplierDto.getAddressLine2());
        assertEquals("Tuticorin", supplierDto.getCity());
        assertEquals("1234567890", supplierDto.getContactNumber());
        assertEquals("abc@gmail.com", supplierDto.getEmail());
        assertEquals(10, supplierDto.getProfitSharingPercentage());
    }
    @Test
    void getSupplierById1() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> supplierService.getSupplierById(2L));
    }

    @Test
    void getAllSuppliers() {
        List<SupplierDto> supplierDtos = supplierService.getAllSuppliers();
        assertEquals("ABC", supplierDtos.get(0).getCompany());
        assertEquals("supplierName", supplierDtos.get(0).getSupplierName());
        assertEquals("123", supplierDtos.get(0).getAddressLine1());
        assertEquals("Abc testing", supplierDtos.get(0).getAddressLine2());
        assertEquals("Tuticorin", supplierDtos.get(0).getCity());
        assertEquals("1234567890", supplierDtos.get(0).getContactNumber());
        assertEquals("abc@gmail.com", supplierDtos.get(0).getEmail());
        assertEquals(10, supplierDtos.get(0).getProfitSharingPercentage());
    }

    @Test
    void updateSupplier() {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setCompany("ABC");
        supplierDto.setAddressLine1("123");
        supplierDto.setAddressLine2("Abc testing");
        supplierDto.setCity("Tuticorin");
        supplierDto.setContactNumber("1234567890");
        supplierDto.setEmail("abc@gmail.com");
        supplierDto.setProfitSharingPercentage(10);
        SupplierDto supplierDto1 = supplierService.updateSupplier(1L, supplierDto);
        assertEquals("ABC", supplierDto1.getCompany());
        assertEquals("123", supplierDto1.getAddressLine1());
        assertEquals("Abc testing", supplierDto1.getAddressLine2());
        assertEquals("Tuticorin", supplierDto1.getCity());
        assertEquals("1234567890", supplierDto1.getContactNumber());
        assertEquals("abc@gmail.com", supplierDto1.getEmail());
        assertEquals(10, supplierDto1.getProfitSharingPercentage());
    }
    @Test
    void updateSupplier1() {
        assertThrows(ResourceNotFoundException.class,()->supplierService.updateSupplier(2L, new SupplierDto()));
    }

    @Test
    void deleteSupplier() {
        boolean b = supplierService.deleteSupplier(1L);
        assertTrue(b);
    }
    @Test
    void deleteSupplier1() {
        assertThrows(ResourceNotFoundException.class,()->supplierService.deleteSupplier(2L));
    }
}