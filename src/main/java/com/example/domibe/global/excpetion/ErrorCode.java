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
  INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED,"incorrect password"),

  NOTICE_BOARD_NOT_FOUND(HttpStatus.NOT_FOUND,"notice board not found"),
  POST_NOT_OWNER(HttpStatus.FORBIDDEN,"post not owner"),

  NOT_NULL_OR_BLANK(HttpStatus.BAD_REQUEST,"not null or blank"),
  NOT_FOUND_EVENT(HttpStatus.NOT_FOUND,"not found event");

  private final HttpStatus httpStatus;
  private final String message;
}
