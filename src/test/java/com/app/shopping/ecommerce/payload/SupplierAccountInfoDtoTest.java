package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierAccountInfoDtoTest {
    SupplierAccountInfoDto supplierAccountInfoDto;
    @BeforeEach
    void setUp() {
        supplierAccountInfoDto = new SupplierAccountInfoDto(1L,"accountHolderName","accountNumber","sortCode","bankName","bankBranch");
    }

    @Test
    void getId() {
        assertEquals(1L,supplierAccountInfoDto.getId());
    }

    @Test
    void getAccountHolderName() {
        assertEquals("accountHolderName",supplierAccountInfoDto.getAccountHolderName());
    }

    @Test
    void getAccountNumber() {
        assertEquals("accountNumber",supplierAccountInfoDto.getAccountNumber());
    }

    @Test
    void getSortCode() {
        assertEquals("sortCode",supplierAccountInfoDto.getSortCode());
    }

    @Test
    void getBankName() {
        assertEquals("bankName",supplierAccountInfoDto.getBankName());
    }

    @Test
    void getBankBranch() {
        assertEquals("bankBranch",supplierAccountInfoDto.getBankBranch());
    }

    @Test
    void setId() {
        supplierAccountInfoDto.setId(2L);
        assertEquals(2L,supplierAccountInfoDto.getId());
    }

    @Test
    void setAccountHolderName() {
        supplierAccountInfoDto.setAccountHolderName("newAccountHolderName");
        assertEquals("newAccountHolderName",supplierAccountInfoDto.getAccountHolderName());
    }

    @Test
    void setAccountNumber() {
        supplierAccountInfoDto.setAccountNumber("newAccountNumber");
        assertEquals("newAccountNumber",supplierAccountInfoDto.getAccountNumber());
    }

    @Test
    void setSortCode() {
        supplierAccountInfoDto.setSortCode("newSortCode");
        assertEquals("newSortCode",supplierAccountInfoDto.getSortCode());
    }

    @Test
    void setBankName() {
        supplierAccountInfoDto.setBankName("newBankName");
        assertEquals("newBankName",supplierAccountInfoDto.getBankName());
    }

    @Test
    void setBankBranch() {
        supplierAccountInfoDto.setBankBranch("newBankBranch");
        assertEquals("newBankBranch",supplierAccountInfoDto.getBankBranch());
    }
}