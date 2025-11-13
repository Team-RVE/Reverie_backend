package com.example.rve.global.handler.config;

import com.example.rve.global.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(CsrfConfigurer::disable)
        .logout(LogoutConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        .httpBasic(HttpBasicConfigurer::disable)

        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(sessionManagement ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(exception ->exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .cors(cors -> {})

        //나중에 수정
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().permitAll())
        .build();
  }
}
