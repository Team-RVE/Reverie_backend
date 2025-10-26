package com.example.rve.global.error;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  JWT_EXPIRED(HttpStatus.UNAUTHORIZED,"token is expired"),
  JWT_INVALID(HttpStatus.UNAUTHORIZED,"token is invalid"),
  JWT_NOT_FOUND(HttpStatus.UNAUTHORIZED,"token not found");
  private final HttpStatus httpStatus;
  private final String message;

}
