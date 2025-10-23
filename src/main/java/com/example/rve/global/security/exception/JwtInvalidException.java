package com.example.rve.global.security.exception;

import com.example.rve.global.error.ErrorCode;
import com.example.rve.global.error.RveException;

public class JwtInvalidException extends RveException {

  public JwtInvalidException() {
    super(ErrorCode.JWT_INVALID);
  }
}
