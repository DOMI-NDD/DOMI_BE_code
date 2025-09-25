package com.example.domibe.global.handler;

import com.example.domibe.domain.auth.exception.AccountIdAlreadyExistsException;
import com.example.domibe.domain.auth.exception.AccountNotFoundException;
import com.example.domibe.domain.auth.exception.IncorrectPasswordException;
import com.example.domibe.domain.noticeboard.exception.NoticeBoardNotFoundException;
import com.example.domibe.domain.noticeboard.exception.PostNotOwnerException;
import com.example.domibe.global.excpetion.ErrorCode;
import com.example.domibe.global.handler.dto.ErrorResponseDto;
import com.example.domibe.global.security.exception.JwtExpiredException;
import com.example.domibe.global.security.exception.JwtInvalidException;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException e) {
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("NOT_NULL_OR_BLANK",ErrorCode.NOT_NULL_OR_BLANK.getMessage()));
 }

 @ExceptionHandler(JwtExpiredException.class)
 public ResponseEntity<ErrorResponseDto> handleJwtExpiredTokenException(JwtExpiredException e) {
    return ResponseEntity.status(ErrorCode.EXPIRED_TOKEN.getHttpStatus()).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler({JwtInvalidException.class})
 public ResponseEntity<ErrorResponseDto> handleJwtInvalidTokenException(JwtInvalidException e) {
   return ResponseEntity.status(ErrorCode.INVALID_TOKEN.getHttpStatus()).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(AccountIdAlreadyExistsException.class)
 public ResponseEntity<ErrorResponseDto> handleAccountIdAlreadyExistsException(AccountIdAlreadyExistsException e) {
   return ResponseEntity.status(ErrorCode.ACCOUNT_ID_ALREADY_EXISTS.getHttpStatus()).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(AccountNotFoundException.class)
 public ResponseEntity<ErrorResponseDto> handleAccountNotFoundException(AccountNotFoundException e) {
  return ResponseEntity.status(ErrorCode.ACCOUNT_NOT_FOUND.getHttpStatus()).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(IncorrectPasswordException.class)
 public ResponseEntity<ErrorResponseDto> handleIncorrectPasswordException(IncorrectPasswordException e) {
  return ResponseEntity.status(ErrorCode.INCORRECT_PASSWORD.getHttpStatus()).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(NoticeBoardNotFoundException.class)
 public ResponseEntity<ErrorResponseDto> handleNoticeBoardNotFoundException(NoticeBoardNotFoundException e) {
  return ResponseEntity.status(ErrorCode.NOTICE_BOARD_NOT_FOUND.getHttpStatus()).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

 @ExceptionHandler(PostNotOwnerException.class)
 public ResponseEntity<ErrorResponseDto> handlePostNotOwnerException(PostNotOwnerException e) {
  return ResponseEntity.status(ErrorCode.POST_NOT_OWNER.getHttpStatus()).body(new ErrorResponseDto(e.getErrorCode().name(),e.getMessage()));
 }

}
