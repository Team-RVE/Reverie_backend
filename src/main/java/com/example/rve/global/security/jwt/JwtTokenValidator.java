package com.example.rve.global.security.jwt;

import com.example.rve.global.security.exception.JwtExpiredException;
import com.example.rve.global.security.exception.JwtInvalidException;
import com.example.rve.global.security.exception.NotFoundJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

  private final JwtTokenGenerator jwtTokenGenerator;
  private final RedisTemplate<String, String> redisTemplate;
  private static final String REDIS_PREFIX = "RT:";

  public boolean validateToken(String token) {
    SecretKey secretKey = jwtTokenGenerator.getSecretKey();
    try{
      Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token)
          .getBody();
      return true;

    }catch(ExpiredJwtException e){
      throw new JwtExpiredException();
    }catch (JwtException e){
      throw new JwtInvalidException();
    }

  }

  public void validateRefreshToken(String accountId, String refreshToken) {
    String key = REDIS_PREFIX + accountId;
    String storedRefreshToken =redisTemplate.opsForValue().get(key);
    if (storedRefreshToken == null) {
      throw new NotFoundJwtException();
    }

    if (!refreshToken.equals(storedRefreshToken)) {
      throw new JwtInvalidException();
    }
    validateToken(refreshToken);
  }

  public Claims getClaims(String token) {
    SecretKey secretKey = jwtTokenGenerator.getSecretKey();

    try{
      return Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token)
          .getBody();
    }catch(ExpiredJwtException e){
      throw new JwtExpiredException();
    }catch (JwtException e){
      throw new JwtInvalidException();
    }
  }
}
