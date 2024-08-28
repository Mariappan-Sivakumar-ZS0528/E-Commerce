package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    Role role;
    @BeforeEach
    void setUp() {
        role =new Role(1L,"ADMIN");
    }

    @Test
    void getId() {
        assertEquals(1L,role.getId());
    }

    @Test
    void getName() {
        assertEquals("ADMIN",role.getName());
    }

    @Test
    void setId() {
        role.setId(2L);
        assertEquals(2L,role.getId());
    }

    @Test
    void setName() {
        role.setName("USER");
        assertEquals("USER",role.getName());
    }
}