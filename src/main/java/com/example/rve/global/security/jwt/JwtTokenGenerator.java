package com.example.rve.global.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Jwts;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Getter
public class JwtTokenGenerator {

  private final JwtProperties jwtProperties;
  private final RedisTemplate<String, String> redisTemplate;
  private static final String ACCESS_TOKEN = "access_token";
  private static final String REFRESH_TOKEN = "refresh_token";
  private static final String REDIS_PREFIX = "RT:";


  private SecretKey secretKey;

  @PostConstruct
  public void init() {
    this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
  }

  public String generateAccessToken(String accountId) {
    return generateToken(accountId,ACCESS_TOKEN,jwtProperties.getAccessExp());
  }

  public String generateRefreshToken(String accountId) {
    String refreshToken = generateToken(accountId,REFRESH_TOKEN,jwtProperties.getRefreshExp());
    String key= REDIS_PREFIX + accountId;
    redisTemplate.opsForValue().set(key,refreshToken,jwtProperties.getRefreshExp(), TimeUnit.MILLISECONDS);
    return refreshToken;
  }

  public String generateToken(String accountId, String type,Long time) {
    Date now = new Date();
    return Jwts.builder()
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .setSubject(accountId)
        .setHeaderParam("typ",type)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime()+time))
        .compact();
  }
}
