package com.example.rve.global.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtProperties {
  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.refresh-exp}")
  private long refreshExp;

  @Value("${jwt.access-exp}")
  private long accessExp;
}
