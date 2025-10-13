package com.example.rve.global.security.exception;

import com.example.rve.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundJwtException extends RuntimeException {

  private final ErrorCode errorCode;

  public NotFoundJwtException() {
    super(ErrorCode.JWT_NOT_FOUND.getMessage());
    this.errorCode = ErrorCode.JWT_NOT_FOUND;
  }
}
