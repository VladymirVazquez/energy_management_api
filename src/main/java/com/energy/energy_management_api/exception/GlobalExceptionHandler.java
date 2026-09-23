package com.energy.energy_management_api.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice 
public class GlobalExceptionHandler {
  
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handle(ResourceNotFoundException e){
      return ResponseEntity.status(404).body(new ErrorResponse(404, e.getMessage(), LocalDateTime.now()));
  
  } 

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e){
    return ResponseEntity.status(400).body(new ErrorResponse(400, e.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e){
    return ResponseEntity.status(400).body(new ErrorResponse(400, e.getConstraintViolations().iterator().next().getMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<ErrorResponse> handle(InvalidRequestException e){
    return ResponseEntity.status(400).body(new ErrorResponse(400, e.getMessage(), LocalDateTime.now()));
  }

  
}