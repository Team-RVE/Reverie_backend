package com.example.rve.global.security.exception;

import com.example.rve.global.error.ErrorCode;
import com.example.rve.global.error.RveException;


public class JwtExpiredException extends RveException {
  public JwtExpiredException() {
    super(ErrorCode.JWT_EXPIRED);

  }
}
