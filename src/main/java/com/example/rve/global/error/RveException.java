package com.example.rve.global.error;

import lombok.Getter;

@Getter
public class RveException extends RuntimeException {

  private final ErrorCode errorCode;

  public RveException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }
}
