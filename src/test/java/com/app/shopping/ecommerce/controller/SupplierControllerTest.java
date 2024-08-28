package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ShowDetailsDto;
import com.app.shopping.ecommerce.payload.SupplierContactDto;
import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierReg;
import com.app.shopping.ecommerce.services.ShowDetailsService;
import com.app.shopping.ecommerce.services.SupplierService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SupplierControllerTest {
    @Mock
    private SupplierService supplierService;
    @Mock
    private ShowDetailsService showDetailsService;
    @InjectMocks
    private SupplierController supplierController;

    @Test
    void addSupplier() {
        SupplierReg supplierReg = new SupplierReg();
        when(supplierService.registrationSupplier(supplierReg)).thenReturn(supplierReg);
        assertEquals(supplierReg, supplierController.addSupplier(supplierReg).getBody());
    }

    @Test
    void getAllSuppliers() {
        SupplierDto supplierDto = new SupplierDto();
        when(supplierService.getAllSuppliers()).thenReturn(List.of(supplierDto));
        assertEquals(List.of(supplierDto), supplierController.getAllSuppliers().getBody());
    }

    @Test
    void getSupplierById() {
        SupplierDto supplierDto = new SupplierDto();
        when(supplierService.getSupplierById(1L)).thenReturn(supplierDto);
        assertEquals(supplierDto, supplierController.getSupplierById(1L).getBody());
    }

    @Test
    void updateSupplier() {
        SupplierDto supplierDto = new SupplierDto();
        when(supplierService.updateSupplier(1L, supplierDto)).thenReturn(supplierDto);
        assertEquals(supplierDto, supplierController.updateSupplier(1L, supplierDto).getBody());
    }

    @Test
    void deleteSupplierForSuccess() {
        when(supplierService.deleteSupplier(1L)).thenReturn(true);
        assertEquals(true, supplierController.deleteSupplier(1L).getBody());
    }
    @Test
    void deleteSupplierForFailure() {
        when(supplierService.deleteSupplier(1L)).thenReturn(false);
        assertFalse(supplierController.deleteSupplier(1L).getBody());
    }

    @Test
    void getSupplierDetails() {
        ShowDetailsDto showDetailsDto = new ShowDetailsDto();
        HttpServletRequest request = new MockHttpServletRequest();
        when(showDetailsService.getShowDetails(request)).thenReturn(showDetailsDto);
        assertEquals(showDetailsDto, supplierController.getSupplierDetails(request).getBody());
    }

    @Test
    void updateSupplierContact() {
        SupplierContactDto supplierContactDto = new SupplierContactDto();
        when(supplierService.UpdateSupplierContact(1L, supplierContactDto)).thenReturn(supplierContactDto);
        assertEquals(supplierContactDto, supplierController.updateSupplierContact(1L, supplierContactDto).getBody());
    }

    @Test
    void getSupplierContactById() {
        SupplierContactDto supplierContactDto = new SupplierContactDto();
        when(supplierService.getSupplierContactById(1L)).thenReturn(supplierContactDto);
        assertEquals(supplierContactDto, supplierController.getSupplierContactById(1L).getBody());
    }
}