package com.example.bankserversystem.config;

import com.example.bankserversystem.jwt.JwtAuthenticationFilter;
import com.example.bankserversystem.jwt.JwtProviders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtProviders jwtProviders;

    public SecurityConfig(JwtProviders jwtProviders) {
        this.jwtProviders = jwtProviders;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/login").permitAll();
                    request.requestMatchers("/api/signUp").permitAll();
                    request.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtProviders), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
