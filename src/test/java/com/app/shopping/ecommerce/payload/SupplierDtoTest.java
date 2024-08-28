package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDtoTest {
    SupplierDto supplierDto;
    @BeforeEach
    void setUp() {
        supplierDto = new SupplierDto(1L, "abc@gmail.com", "abc", 0.0, "1234567890", "address1", "address2", "address3", "city", "1234", "supplier", "designation", "accountHolder", "accountNumber", "sortCode", "bankBranch");
    }

    @Test
    void getId() {
        assertEquals(1L, supplierDto.getId());
    }

    @Test
    void getEmail() {
        assertEquals("abc@gmail.com", supplierDto.getEmail());
    }

    @Test
    void getCompany() {
        assertEquals("abc", supplierDto.getCompany());
    }

    @Test
    void getProfitSharingPercentage() {
        assertEquals(0.0, supplierDto.getProfitSharingPercentage());
    }

    @Test
    void getContactNumber() {
        assertEquals("1234567890", supplierDto.getContactNumber());
    }

    @Test
    void getAddressLine1() {
        assertEquals("address1", supplierDto.getAddressLine1());
    }

    @Test
    void getAddressLine2() {
        assertEquals("address2", supplierDto.getAddressLine2());
    }

    @Test
    void getAddressLine3() {
        assertEquals("address3", supplierDto.getAddressLine3());
    }

    @Test
    void getCity() {
        assertEquals("city", supplierDto.getCity());
    }

    @Test
    void getPostalCode() {
        assertEquals("1234", supplierDto.getPostalCode());
    }

    @Test
    void getSupplierName() {
        assertEquals("supplier", supplierDto.getSupplierName());
    }

    @Test
    void getDesignation() {
        assertEquals("designation", supplierDto.getDesignation());
    }

    @Test
    void getAccountHolder() {
        assertEquals("accountHolder", supplierDto.getAccountHolder());
    }

    @Test
    void getAccountNumber() {
        assertEquals("accountNumber", supplierDto.getAccountNumber());
    }

    @Test
    void getSortCode() {
        assertEquals("sortCode", supplierDto.getSortCode());
    }

    @Test
    void getBankBranch() {
        assertEquals("bankBranch", supplierDto.getBankBranch());
    }

    @Test
    void setId() {
        supplierDto.setId(2L);
        assertEquals(2L, supplierDto.getId());
    }

    @Test
    void setEmail() {
        supplierDto.setEmail("abc2@gmail.com");
        assertEquals("abc2@gmail.com", supplierDto.getEmail());
    }

    @Test
    void setCompany() {
        supplierDto.setCompany("abc2");
        assertEquals("abc2", supplierDto.getCompany());
    }

    @Test
    void setProfitSharingPercentage() {
        supplierDto.setProfitSharingPercentage(10.0);
        assertEquals(10.0, supplierDto.getProfitSharingPercentage());
    }

    @Test
    void setContactNumber() {
        supplierDto.setContactNumber("1234567891");
        assertEquals("1234567891", supplierDto.getContactNumber());
    }

    @Test
    void setAddressLine1() {
        supplierDto.setAddressLine1("newAddressLine1");
        assertEquals("newAddressLine1", supplierDto.getAddressLine1());
    }

    @Test
    void setAddressLine2() {
        supplierDto.setAddressLine2("newAddressLine2");
        assertEquals("newAddressLine2", supplierDto.getAddressLine2());
    }

    @Test
    void setAddressLine3() {
        supplierDto.setAddressLine3("newAddressLine3");
        assertEquals("newAddressLine3", supplierDto.getAddressLine3());
    }

    @Test
    void setCity() {
        supplierDto.setCity("newCity");
        assertEquals("newCity", supplierDto.getCity());
    }

    @Test
    void setPostalCode() {
        supplierDto.setPostalCode("1235");
        assertEquals("1235", supplierDto.getPostalCode());
    }

    @Test
    void setSupplierName() {
        supplierDto.setSupplierName("newSupplierName");
        assertEquals("newSupplierName", supplierDto.getSupplierName());
    }

    @Test
    void setDesignation() {
        supplierDto.setDesignation("newDesignation");
        assertEquals("newDesignation", supplierDto.getDesignation());
    }

    @Test
    void setAccountHolder() {
        supplierDto.setAccountHolder("newAccountHolder");
        assertEquals("newAccountHolder", supplierDto.getAccountHolder());
    }

    @Test
    void setAccountNumber() {
        supplierDto.setAccountNumber("newAccountNumber");
        assertEquals("newAccountNumber", supplierDto.getAccountNumber());
    }

    @Test
    void setSortCode() {
        supplierDto.setSortCode("newSortCode");
        assertEquals("newSortCode", supplierDto.getSortCode());
    }

    @Test
    void setBankBranch() {
        supplierDto.setBankBranch("newBankBranch");
        assertEquals("newBankBranch", supplierDto.getBankBranch());
    }
}