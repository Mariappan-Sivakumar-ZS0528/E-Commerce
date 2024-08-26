package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerUpdatePasswordTest {
    CustomerUpdatePassword customerUpdatePassword;

    @BeforeEach
    void setUp() {
        customerUpdatePassword = new CustomerUpdatePassword();
        customerUpdatePassword = new CustomerUpdatePassword(1L, "oldPassword", "newPassword", "confirmPassword");
    }

    @Test
    void getId() {
        assertEquals(1L, customerUpdatePassword.getId());
    }

    @Test
    void getOldPassword() {
        assertEquals("oldPassword", customerUpdatePassword.getOldPassword());
    }

    @Test
    void getNewPassword() {
        assertEquals("newPassword", customerUpdatePassword.getNewPassword());
    }

    @Test
    void getConfirmPassword() {
        assertEquals("confirmPassword", customerUpdatePassword.getConfirmPassword());
    }

    @Test
    void setId() {
        customerUpdatePassword.setId(2L);
        assertEquals(2L, customerUpdatePassword.getId());
    }

    @Test
    void setOldPassword() {
        customerUpdatePassword.setOldPassword("oldPassword1");
        assertEquals("oldPassword1", customerUpdatePassword.getOldPassword());
    }

    @Test
    void setNewPassword() {
        customerUpdatePassword.setNewPassword("newPassword1");
        assertEquals("newPassword1", customerUpdatePassword.getNewPassword());
    }

    @Test
    void setConfirmPassword() {
        customerUpdatePassword.setConfirmPassword("confirmPassword1");
        assertEquals("confirmPassword1", customerUpdatePassword.getConfirmPassword());
    }
}