package com.microservices.Services.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.microservices.Services.config.filter.JwtTokenValidator;
import com.microservices.Services.utils.JWTutils;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private JWTutils jwtUtils;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {

                    http.requestMatchers(HttpMethod.GET, "/upload/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/services/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/api/services/**").permitAll();
                    http.requestMatchers(HttpMethod.PUT, "/api/services/**").permitAll();
                    http.requestMatchers(HttpMethod.DELETE, "/api/services/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/api/categories/**").permitAll();
                    http.requestMatchers(HttpMethod.PUT, "/api/categories/**").permitAll();
                    http.requestMatchers(HttpMethod.DELETE, "/api/categories/**").permitAll();
                    http.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
