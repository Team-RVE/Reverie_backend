package com.example.rve.global.security.exception;

import com.example.rve.global.error.ErrorCode;
import com.example.rve.global.error.RveException;

public class NotFoundJwtException extends RveException {

  public NotFoundJwtException() {
    super(ErrorCode.JWT_NOT_FOUND);
  }
}
