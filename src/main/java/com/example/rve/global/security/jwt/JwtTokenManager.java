package com.example.rve.global.security.jwt;

import com.example.rve.global.security.auth.CustomUserDetails;
import com.example.rve.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenManager {

  private final JwtTokenGenerator jwtTokenGenerator;
  private final JwtTokenValidator jwtTokenValidator;
  private final CustomUserDetailsService authDetailsService;


  private static final String REDIS_PREFIX = "RT:";

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
  public String reissueAccessToken(String accountId,String refreshToken) {
    jwtTokenValidator.validateRefreshToken(accountId, refreshToken);
    return jwtTokenGenerator.generateAccessToken(accountId);
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String token) {
    Claims claims = jwtTokenValidator.getClaims(token);
    CustomUserDetails userDetails = (CustomUserDetails) authDetailsService.loadUserByUsername(claims.getSubject());
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
}
