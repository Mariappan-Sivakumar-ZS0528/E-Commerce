package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {
    Supplier supplier;
    Set<Product> products=Set.of(new Product());
    @BeforeEach
    void setUp() {
        supplier = new Supplier(1L,"raj@gmail.com","ABC",1.2,"9360409963","abc1","abc2","abc3","paris","666","mari","Admin","mari","12345","abc","abc",products);
    }
    @Test
    void getId() {
        assertEquals(1L,supplier.getId());
    }

    @Test
    void getEmail() {
        assertEquals("raj@gmail.com",supplier.getEmail());
    }

    @Test
    void getCompany() {
        assertEquals("ABC",supplier.getCompany());
    }

    @Test
    void getProfitSharingPercentage() {
        assertEquals(1.2,supplier.getProfitSharingPercentage());
    }

    @Test
    void getContactNumber() {
        assertEquals("9360409963",supplier.getContactNumber());
    }

    @Test
    void getAddressLine1() {
        assertEquals("abc1",supplier.getAddressLine1());
    }

    @Test
    void getAddressLine2() {
        assertEquals("abc2",supplier.getAddressLine2());
    }

    @Test
    void getAddressLine3() {
        assertEquals("abc3",supplier.getAddressLine3());
    }

    @Test
    void getCity() {
        assertEquals("paris",supplier.getCity());
    }

    @Test
    void getPostalCode() {
        assertEquals("666",supplier.getPostalCode());
    }

    @Test
    void getSupplierName() {
        assertEquals("mari",supplier.getSupplierName());
    }

    @Test
    void getDesignation() {
        assertEquals("Admin",supplier.getDesignation());
    }

    @Test
    void getAccountHolder() {
        assertEquals("mari",supplier.getAccountHolder());
    }

    @Test
    void getAccountNumber() {
        assertEquals("12345",supplier.getAccountNumber());
    }

    @Test
    void getSortCode() {
        assertEquals("abc",supplier.getSortCode());
    }

    @Test
    void getBankBranch() {
        assertEquals("abc",supplier.getBankBranch());
    }

    @Test
    void getProducts() {
        assertEquals(products,supplier.getProducts());
    }

    @Test
    void setId() {
        supplier.setId(2L);
        assertEquals(2L,supplier.getId());
    }

    @Test
    void setEmail() {
        supplier.setEmail("raj2@gmail.com");
        assertEquals("raj2@gmail.com",supplier.getEmail());
    }

    @Test
    void setCompany() {
        supplier.setCompany("ABC2");
        assertEquals("ABC2",supplier.getCompany());
    }

    @Test
    void setProfitSharingPercentage() {
        supplier.setProfitSharingPercentage(2.2);
        assertEquals(2.2,supplier.getProfitSharingPercentage());
    }

    @Test
    void setContactNumber() {
        supplier.setContactNumber("9360409964");
        assertEquals("9360409964",supplier.getContactNumber());
    }

    @Test
    void setAddressLine1() {
        supplier.setAddressLine1("abc4");
        assertEquals("abc4",supplier.getAddressLine1());
    }

    @Test
    void setAddressLine2() {
        supplier.setAddressLine2("abc5");
        assertEquals("abc5",supplier.getAddressLine2());
    }

    @Test
    void setAddressLine3() {
        supplier.setAddressLine3("abc6");
        assertEquals("abc6",supplier.getAddressLine3());
    }

    @Test
    void setCity() {
        supplier.setCity("paris1");
        assertEquals("paris1",supplier.getCity());
    }

    @Test
    void setPostalCode() {
        supplier.setPostalCode("6661");
        assertEquals("6661",supplier.getPostalCode());
    }

    @Test
    void setSupplierName() {
        supplier.setSupplierName("mari1");
        assertEquals("mari1",supplier.getSupplierName());
    }

    @Test
    void setDesignation() {
        supplier.setDesignation("Admin1");
        assertEquals("Admin1",supplier.getDesignation());
    }

    @Test
    void setAccountHolder() {
        supplier.setAccountHolder("mari2");
        assertEquals("mari2",supplier.getAccountHolder());
    }

    @Test
    void setAccountNumber() {
        supplier.setAccountNumber("123451");
        assertEquals("123451",supplier.getAccountNumber());
    }

    @Test
    void setSortCode() {
        supplier.setSortCode("abc1");
        assertEquals("abc1",supplier.getSortCode());
    }

    @Test
    void setBankBranch() {
        supplier.setBankBranch("abc2");
        assertEquals("abc2",supplier.getBankBranch());
    }

    @Test
    void setProducts() {
        supplier.setProducts(products);
        assertEquals(products,supplier.getProducts());
    }
}