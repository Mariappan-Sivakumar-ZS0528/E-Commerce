package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Role;
import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.AdminRegistrationDto;
import com.app.shopping.ecommerce.payload.LoginDto;
import com.app.shopping.ecommerce.payload.SupplierPassword;
import com.app.shopping.ecommerce.repository.RoleRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.repository.UserRepository;
import com.app.shopping.ecommerce.security.JwtTokenProvider;
import com.app.shopping.ecommerce.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private SupplierRepository supplierRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, SupplierRepository supplierRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }

    @Override
    public AdminRegistrationDto registerAdmin(AdminRegistrationDto adminRegistrationDto) {
        if (userRepository.existsByEmail(adminRegistrationDto.getEmail())){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Email already exists");
        }
        User user=new User();
        user.setEmail(adminRegistrationDto.getEmail());
        user.setName(adminRegistrationDto.getName());
        user.setContact(adminRegistrationDto.getContact());
        user.setRole(adminRegistrationDto.getRole());
        if (adminRegistrationDto.getRole().equals("admin")){
            Role roles=(roleRepository.findByName("ROLE_ADMIN").get());
            user.setRoles(Set.of(roles));
        } else if (adminRegistrationDto.getRole().equals("market")) {
            Role roles=(roleRepository.findByName("ROLE_MARKET").get());
            user.setRoles(Set.of(roles));
        } else if (adminRegistrationDto.getRole().equals("finance")) {
            Role roles=(roleRepository.findByName("ROLE_FINANCE").get());
            user.setRoles(Set.of(roles));
        }
        User savedUser=userRepository.save(user);
        return modelMapper.map(savedUser, AdminRegistrationDto.class);
    }

    @Override
    public List<AdminRegistrationDto> getAllAdmins() {
        List<Role> roles=new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_MARKET").get());
        roles.add(roleRepository.findByName("ROLE_FINANCE").get());
        roles.add(roleRepository.findByName("ROLE_ADMIN").get());
        List<User> users=userRepository.findAll().stream().filter(user -> user.getRoles().contains(roles.get(0)) || user.getRoles().contains(roles.get(1)) || user.getRoles().contains(roles.get(2))).toList();
        return users.stream().map(user -> modelMapper.map(user, AdminRegistrationDto.class)).toList();
    }

    public String setSupplierPassword(Long id, SupplierPassword supplierPassword) {
        if (! (supplierPassword.getPassword().equals(supplierPassword.getConfirmPassword())&& supplierPassword.getPassword().length()>8)){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Password does not match");
        }
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));
        if (supplier.getEmail().equals(supplierPassword.getEmail())){
            User user=new User();
            user.setEmail(supplier.getEmail());
            user.setContact(supplier.getContactNumber());
            user.setName(supplier.getCompany());
            Set<Role> roles=new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_MERCHANT").get());
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(supplierPassword.getPassword()));
            userRepository.save(user);
            return "Password updated successfully";
        }
        else {
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Email does not match");
        }
    }
}
