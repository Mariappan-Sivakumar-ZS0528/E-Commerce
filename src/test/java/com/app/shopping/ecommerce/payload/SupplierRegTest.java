package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierRegTest {
    SupplierReg supplierReg;
    @BeforeEach
    void setUp() {
        supplierReg = new SupplierReg(1L,"email","company",0.0,"1234567890","address1","address2","address3","city","1234");
    }

    @Test
    void getId() {
        assertEquals(1L,supplierReg.getId());
    }

    @Test
    void getEmail() {
        assertEquals("email",supplierReg.getEmail());
    }

    @Test
    void getCompany() {
        assertEquals("company",supplierReg.getCompany());
    }

    @Test
    void getProfitSharingPercentage() {
        assertEquals(0.0,supplierReg.getProfitSharingPercentage());
    }

    @Test
    void getContactNumber() {
        assertEquals("1234567890",supplierReg.getContactNumber());
    }

    @Test
    void getAddressLine1() {
        assertEquals("address1",supplierReg.getAddressLine1());
    }

    @Test
    void getAddressLine2() {
        assertEquals("address2",supplierReg.getAddressLine2());
    }

    @Test
    void getAddressLine3() {
        assertEquals("address3",supplierReg.getAddressLine3());
    }

    @Test
    void getCity() {
        assertEquals("city",supplierReg.getCity());
    }

    @Test
    void getPostalCode() {
        assertEquals("1234",supplierReg.getPostalCode());
    }

    @Test
    void setId() {
        supplierReg.setId(2L);
        assertEquals(2L,supplierReg.getId());
    }

    @Test
    void setEmail() {
        supplierReg.setEmail("email2");
        assertEquals("email2",supplierReg.getEmail());
    }

    @Test
    void setCompany() {
        supplierReg.setCompany("company2");
        assertEquals("company2",supplierReg.getCompany());
    }

    @Test
    void setProfitSharingPercentage() {
        supplierReg.setProfitSharingPercentage(0.1);
        assertEquals(0.1,supplierReg.getProfitSharingPercentage());
    }

    @Test
    void setContactNumber() {
        supplierReg.setContactNumber("1234567891");
        assertEquals("1234567891",supplierReg.getContactNumber());
    }

    @Test
    void setAddressLine1() {
        supplierReg.setAddressLine1("newAddressLine1");
        assertEquals("newAddressLine1",supplierReg.getAddressLine1());
    }

    @Test
    void setAddressLine2() {
        supplierReg.setAddressLine2("newAddressLine2");
        assertEquals("newAddressLine2",supplierReg.getAddressLine2());
    }

    @Test
    void setAddressLine3() {
        supplierReg.setAddressLine3("newAddressLine3");
        assertEquals("newAddressLine3",supplierReg.getAddressLine3());
    }

    @Test
    void setCity() {
        supplierReg.setCity("newCity");
        assertEquals("newCity",supplierReg.getCity());
    }

    @Test
    void setPostalCode() {
        supplierReg.setPostalCode("1235");
        assertEquals("1235",supplierReg.getPostalCode());
    }
}