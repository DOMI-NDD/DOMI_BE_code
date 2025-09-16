package com.example.domibe.global.excpetion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;



@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED,"token is expired"),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"token is invalid"),

  ACCOUNT_ID_ALREADY_EXISTS(HttpStatus.CONFLICT,"account already exists"),
  ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND,"account not found"),
  INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED,"incorrect password");

  private final HttpStatus httpStatus;
  private final String message;
}
