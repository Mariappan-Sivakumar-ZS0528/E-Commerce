package com.app.shopping.ecommerce.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("mariappan"));
        System.out.println(passwordEncoder.encode("balaji"));
        System.out.println(passwordEncoder.encode("jayaanand"));
        System.out.println(passwordEncoder.encode("rajkumar"));
        System.out.println(passwordEncoder.encode("securePassword123"));
    }
}
