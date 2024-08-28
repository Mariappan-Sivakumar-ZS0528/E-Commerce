package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierPasswordTest {
    SupplierPassword supplierPassword;
    @BeforeEach
    void setUp() {
        supplierPassword = new SupplierPassword("email","password","confirmPassword");
    }
    @Test
    void getEmail() {
        assertEquals("email",supplierPassword.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("password",supplierPassword.getPassword());
    }

    @Test
    void getConfirmPassword() {
        assertEquals("confirmPassword",supplierPassword.getConfirmPassword());
    }

    @Test
    void setEmail() {
        supplierPassword.setEmail("email1");
        assertEquals("email1",supplierPassword.getEmail());
    }

    @Test
    void setPassword() {
        supplierPassword.setPassword("password1");
        assertEquals("password1",supplierPassword.getPassword());
    }

    @Test
    void setConfirmPassword() {
        supplierPassword.setConfirmPassword("confirmPassword1");
        assertEquals("confirmPassword1",supplierPassword.getConfirmPassword());
    }
    @Test
    void testtoNoargs(){
        SupplierPassword supplierPassword = new SupplierPassword();
        assertNotNull(supplierPassword);
    }
}