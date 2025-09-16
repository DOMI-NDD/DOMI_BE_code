package com.example.domibe.domain.auth.exception;

import com.example.domibe.global.excpetion.ErrorCode;
import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {

  private final ErrorCode errorCode;
  public AccountNotFoundException() {
    super(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
    this.errorCode = ErrorCode.ACCOUNT_NOT_FOUND;
  }
}
