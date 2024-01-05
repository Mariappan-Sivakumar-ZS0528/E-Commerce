package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.repository.UserRepository;
import com.app.shopping.ecommerce.services.AuthService;
import com.app.shopping.ecommerce.services.EmailService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@SpringBootTest
class AuthServiceImplTest {
    @Autowired
    private AuthService authService;
    @MockBean
    private EmailService emailService;
    @MockBean
    private UserRepository userRepository;

    @Test
    void sendPasswordResetPin()
    {
        User user = new User();
        user.setEmail("email");
        user.setName("name");
        user.setId(1L);
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findByEmail("email")).thenReturn(optionalUser);
        Mockito.when(userRepository.findByEmail("email1")).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(authService.sendPasswordResetPin("email"),"Password reset PIN sent to your email");
        assertThrows(ResourceNotFoundException.class, () -> authService.sendPasswordResetPin("email1"));

    }

    @Test
    void processPasswordReset()
    {
        User user = new User();
        user.setEmail("email");
        user.setName("name");
        user.setId(1L);
        user.setPin("123456");

        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findByEmail("email")).thenReturn(optionalUser);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(authService.processPasswordReset("email","123456","newPassword"),"Password reset successfully");
        assertEquals(authService.processPasswordReset("email","123756","newPassword"),"Invalid PIN");
    }
}