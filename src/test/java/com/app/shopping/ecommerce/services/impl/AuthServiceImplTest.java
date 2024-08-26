package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Role;
import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.payload.AdminRegistrationDto;
import com.app.shopping.ecommerce.payload.LoginDto;
import com.app.shopping.ecommerce.payload.SupplierPassword;
import com.app.shopping.ecommerce.repository.RoleRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.repository.UserRepository;
import com.app.shopping.ecommerce.security.JwtTokenProvider;
import com.app.shopping.ecommerce.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    AuthServiceImpl authService;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    SupplierRepository supplierRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    Authentication authentication;

    AdminRegistrationDto adminRegistrationDto;
    List<AdminRegistrationDto> adminRegistrationDtos;

    User user;
    List<User> users;
    Role role;
    SupplierPassword supplierPassword;
    Supplier supplier;


    @BeforeEach
    void setUp() {
        authentication=Mockito.mock(Authentication.class);
//        Integer id=Integer.parseInt()
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        Mockito.when(jwtTokenProvider.generateToken(authentication)).thenReturn("token");

        adminRegistrationDto=new AdminRegistrationDto(1L, "name", "email", "contact", "admin");
        adminRegistrationDtos=List.of(adminRegistrationDto);
        Role role=new Role(1L, "ADMIN");
        user=new User(1L, "name", "email", "password","contact","null", Set.of(role));
        users=List.of(user);
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(modelMapper.map(adminRegistrationDto, User.class)).thenReturn(user);
        Mockito.when(modelMapper.map(user, AdminRegistrationDto.class)).thenReturn(adminRegistrationDto);
        Mockito.when(roleRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(role));
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("passwordTest");

        supplier=new Supplier();
        supplier.setEmail("example@gmail.com");
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);
        supplierPassword=new SupplierPassword("example@gmail.com","passwordTest","passwordTest");

        Mockito.when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
    }

    @Test
    void login() {
        String token = authService.login(new LoginDto());
        assertEquals("token", token);
    }

    @Test
    void registerAdmin_Admin() {
        assertEquals(adminRegistrationDto, authService.registerAdmin(adminRegistrationDto));
    }

    @Test
    void registerAdmin_Market() {
        adminRegistrationDto.setRole("market");
        assertEquals(adminRegistrationDto, authService.registerAdmin(adminRegistrationDto));
    }

    @Test
    void registerAdmin_Finance() {
        adminRegistrationDto.setRole("finance");
        assertEquals(adminRegistrationDto, authService.registerAdmin(adminRegistrationDto));
    }
    @Test
    void registerAdmin_UserExists() {
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        assertThrows(ECommerceApiException.class, () -> authService.registerAdmin(adminRegistrationDto));
    }

    @Test
    void getAllAdmins() {
        assertEquals(adminRegistrationDtos, authService.getAllAdmins());
    }

    @Test
    void setSupplierPassword() {
        assertEquals("Password updated successfully", authService.setSupplierPassword(1L, supplierPassword));
    }

    @Test
    void setSupplierPassword_PasswordLengthLess() {
        supplierPassword.setPassword("password");
        supplierPassword.setConfirmPassword("password");
        assertThrows(ECommerceApiException.class, () -> authService.setSupplierPassword(1L, supplierPassword));
    }
    @Test
    void setSupplierPassword_PasswordNotMatch() {
        supplierPassword.setConfirmPassword("passwordTest1");
        assertThrows(ECommerceApiException.class, () -> authService.setSupplierPassword(1L, supplierPassword));
    }

    @Test
    void setSupplierPassword_EmailNotMatch() {
        supplierPassword.setEmail("example@gmail");
        assertThrows(ECommerceApiException.class, () -> authService.setSupplierPassword(1L, supplierPassword));
    }
}