package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierContactDtoTest {
    SupplierContactDto supplierContactDto;
    @BeforeEach
    void setUp() {
        supplierContactDto = new SupplierContactDto(1L,"contactPerson","designation","contactNumber","email");
    }

    @Test
    void getId() {
        assertEquals(1L,supplierContactDto.getId());
    }

    @Test
    void getContactPerson() {
        assertEquals("contactPerson",supplierContactDto.getContactPerson());
    }

    @Test
    void getDesignation() {
        assertEquals("designation",supplierContactDto.getDesignation());
    }

    @Test
    void getContactNumber() {
        assertEquals("contactNumber",supplierContactDto.getContactNumber());
    }

    @Test
    void getEmail() {
        assertEquals("email",supplierContactDto.getEmail());
    }

    @Test
    void setId() {
        supplierContactDto.setId(2L);
        assertEquals(2L,supplierContactDto.getId());
    }

    @Test
    void setContactPerson() {
        supplierContactDto.setContactPerson("newContactPerson");
        assertEquals("newContactPerson",supplierContactDto.getContactPerson());
    }

    @Test
    void setDesignation() {
        supplierContactDto.setDesignation("newDesignation");
        assertEquals("newDesignation",supplierContactDto.getDesignation());
    }

    @Test
    void setContactNumber() {
        supplierContactDto.setContactNumber("newContactNumber");
        assertEquals("newContactNumber",supplierContactDto.getContactNumber());
    }

    @Test
    void setEmail() {
        supplierContactDto.setEmail("newEmail");
        assertEquals("newEmail",supplierContactDto.getEmail());
    }
}