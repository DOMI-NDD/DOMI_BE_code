package com.example.domibe.global.handler;

import com.example.domibe.domain.auth.exception.AccountIdAlreadyExistsException;
import com.example.domibe.domain.auth.exception.AccountNotFoundException;
import com.example.domibe.domain.auth.exception.IncorrectPasswordException;
import com.example.domibe.global.handler.dto.ErrorResponseDto;
import com.example.domibe.global.security.exception.JwtExpiredException;
import com.example.domibe.global.security.exception.JwtInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(JwtExpiredException.class)
 public ResponseEntity<ErrorResponseDto> handleJwtExpiredTokenException(JwtExpiredException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler({JwtInvalidException.class})
 public ResponseEntity<ErrorResponseDto> handleJwtInvalidTokenException(JwtInvalidException e) {
   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(AccountIdAlreadyExistsException.class)
 public ResponseEntity<ErrorResponseDto> handleAccountIdAlreadyExistsException(AccountIdAlreadyExistsException e) {
   return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(AccountNotFoundException.class)
 public ResponseEntity<ErrorResponseDto> handleAccountNotFoundException(AccountNotFoundException e) {
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(IncorrectPasswordException.class)
 public ResponseEntity<ErrorResponseDto> handleIncorrectPasswordException(IncorrectPasswordException e) {
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

}
