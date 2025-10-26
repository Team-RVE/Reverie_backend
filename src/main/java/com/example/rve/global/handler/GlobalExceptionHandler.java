package com.example.rve.global.handler;

import com.example.rve.global.error.RveException;
import com.example.rve.global.handler.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RveException.class)
  public ResponseEntity<ErrorResponse> handleRveException(RveException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(new ErrorResponse(e.getErrorCode().toString(), e.getMessage()));
  }

}
