package com.example.rve.global.security.exception;

import com.example.rve.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class JwtExpiredException extends RuntimeException {

  private final ErrorCode errorCode;

  public JwtExpiredException() {
    super(ErrorCode.JWT_EXPIRED.getMessage());
    this.errorCode = ErrorCode.JWT_EXPIRED;
  }
}
