package com.example.rve.global.security.exception;

import com.example.rve.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class JwtInvalidException extends RuntimeException {

  private final ErrorCode errorCode;

  public JwtInvalidException() {
    super(ErrorCode.JWT_INVALID.getMessage());
    this.errorCode = ErrorCode.JWT_INVALID;
  }
}
