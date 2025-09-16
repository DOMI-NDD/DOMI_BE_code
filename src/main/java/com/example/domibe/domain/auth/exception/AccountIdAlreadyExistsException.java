package com.example.domibe.domain.auth.exception;

import com.example.domibe.global.excpetion.ErrorCode;
import lombok.Getter;

@Getter
public class AccountIdAlreadyExistsException extends RuntimeException {

  private final ErrorCode errorCode;

  public AccountIdAlreadyExistsException() {
    super(ErrorCode.ACCOUNT_ID_ALREADY_EXISTS.getMessage());
    this.errorCode = ErrorCode.ACCOUNT_ID_ALREADY_EXISTS;
  }

}
