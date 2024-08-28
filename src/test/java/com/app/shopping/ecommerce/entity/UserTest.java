package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    Set<Role> roles= Set.of(new Role());
    @BeforeEach
    void setUp() {
        user=new User(1L,"abc","abc@gmail.com","abc","1234567890","admin",roles);
    }

    @Test
    void getId() {
        assertEquals(1L,user.getId());
    }

    @Test
    void getName() {
        assertEquals("abc",user.getName());
    }

    @Test
    void getEmail() {
        assertEquals("abc@gmail.com",user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("abc",user.getPassword());
    }

    @Test
    void getContact() {
        assertEquals("1234567890",user.getContact());
    }

    @Test
    void getRole() {
        assertEquals("admin",user.getRole());
    }

    @Test
    void getRoles() {
        assertEquals(roles,user.getRoles());
    }

    @Test
    void setId() {
        user.setId(2L);
        assertEquals(2L,user.getId());
    }

    @Test
    void setName() {
        user.setName("xyz");
        assertEquals("xyz",user.getName());
    }

    @Test
    void setEmail() {
        user.setEmail("xyz@gmail.com");
        assertEquals("xyz@gmail.com",user.getEmail());
    }

    @Test
    void setPassword() {
        user.setPassword("xyz");
        assertEquals("xyz",user.getPassword());
    }

    @Test
    void setContact() {
        user.setContact("1234567890");
        assertEquals("1234567890",user.getContact());
    }

    @Test
    void setRole() {
        user.setRole("xyz");
        assertEquals("xyz",user.getRole());
    }

    @Test
    void setRoles() {
        user.setRoles(roles);
        assertEquals(roles,user.getRoles());
    }
}