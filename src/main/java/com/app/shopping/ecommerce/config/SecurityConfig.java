package com.app.shopping.ecommerce.config;

import com.app.shopping.ecommerce.security.JwtAuthenticationEntryPoint;
import com.app.shopping.ecommerce.security.JwtAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(
                (csrf) -> csrf.disable()).authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"api/categories/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"api/products/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"api/products/getProductOffer/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"api/supplier/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"api/faq/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"api/legal/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/pdf/**").permitAll()
                                .requestMatchers("swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/DisplayInTiles/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/FullWidthDisplay/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/product/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/file/download").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).exceptionHandling((exception)->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                ).sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user= User.builder().username("mariappan").password(passwordEncoder().encode("mariappan")).roles("ADMIN").build();
//        UserDetails admin= User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
}
