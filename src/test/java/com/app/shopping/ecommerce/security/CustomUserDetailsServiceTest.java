package com.app.shopping.ecommerce.security;

import com.app.shopping.ecommerce.entity.Role;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService UserDetailsService;

    User user;
    Role role;

    @BeforeEach
    void setUp() {
        role=new Role(1L, "ADMIN");
        user = new User(1L, "name", "email", "password", "number", null, Set.of(role));

        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(user));
    }

    @Test
    void loadUserByUsername() {
        assertEquals(org.springframework.security.core.userdetails.User.class, UserDetailsService.loadUserByUsername("email").getClass());
    }
}