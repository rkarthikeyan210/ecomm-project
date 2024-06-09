package com.ecommproject.userservice.security;

import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/endpoint").hasAuthority("USER")
            .anyRequest().permitAll()
        ).csrf(CsrfConfigurer::disable);

        return http.build();
    }

    @Bean
    SecretKey secretKey() {
        return Jwts.SIG.HS256.key().build();
    }
}
