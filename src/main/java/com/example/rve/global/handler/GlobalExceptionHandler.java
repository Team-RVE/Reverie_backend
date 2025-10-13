package com.example.rve.global.handler;

import com.example.rve.global.handler.dto.ErrorResponse;
import com.example.rve.global.security.exception.JwtExpiredException;
import com.example.rve.global.security.exception.JwtInvalidException;
import com.example.rve.global.security.exception.NotFoundJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(JwtExpiredException.class)
  public ResponseEntity<ErrorResponse> handleJwtExpiredException(JwtExpiredException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getErrorCode().toString(), e.getMessage()));
  }

  @ExceptionHandler(JwtInvalidException.class)
  public ResponseEntity<ErrorResponse> handleJwtInvalidException(JwtInvalidException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getErrorCode().toString(), e.getMessage()));
  }

  @ExceptionHandler(NotFoundJwtException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundJwtException(NotFoundJwtException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getErrorCode().toString(), e.getMessage()));
  }


}
