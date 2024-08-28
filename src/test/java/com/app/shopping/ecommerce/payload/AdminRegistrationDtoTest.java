package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminRegistrationDtoTest {
    AdminRegistrationDto adminRegistrationDto;

    @BeforeEach
    void setUp() {
        adminRegistrationDto=new AdminRegistrationDto();
        adminRegistrationDto = new AdminRegistrationDto(1L, "email", "name", "contact", "role");
    }

    @Test
    void getId() {
        assertEquals(1L, adminRegistrationDto.getId());
    }

    @Test
    void getEmail() {
        assertEquals("email", adminRegistrationDto.getEmail());
    }

    @Test
    void getName() {
        assertEquals("name", adminRegistrationDto.getName());
    }

    @Test
    void getContact() {
        assertEquals("contact", adminRegistrationDto.getContact());
    }

    @Test
    void getRole() {
        assertEquals("role", adminRegistrationDto.getRole());
    }

    @Test
    void setId() {
        adminRegistrationDto.setId(2L);
        assertEquals(2L, adminRegistrationDto.getId());
    }

    @Test
    void setEmail() {
        adminRegistrationDto.setEmail("email1");
        assertEquals("email1", adminRegistrationDto.getEmail());
    }

    @Test
    void setName() {
        adminRegistrationDto.setName("name1");
        assertEquals("name1", adminRegistrationDto.getName());
    }

    @Test
    void setContact() {
        adminRegistrationDto.setContact("contact1");
        assertEquals("contact1", adminRegistrationDto.getContact());
    }

    @Test
    void setRole() {
        adminRegistrationDto.setRole("role1");
        assertEquals("role1", adminRegistrationDto.getRole());
    }
}