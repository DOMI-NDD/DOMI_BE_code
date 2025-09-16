package com.example.domibe.domain.auth.exception;

import com.example.domibe.global.excpetion.ErrorCode;
import lombok.Getter;

@Getter
public class IncorrectPasswordException extends RuntimeException {

  private final ErrorCode errorCode;

  public IncorrectPasswordException() {
    super(ErrorCode.INCORRECT_PASSWORD.getMessage());
    this.errorCode = ErrorCode.INCORRECT_PASSWORD;
  }
}
