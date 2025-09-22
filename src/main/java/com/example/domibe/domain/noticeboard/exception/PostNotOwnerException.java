package com.example.domibe.domain.noticeboard.exception;

import com.example.domibe.global.excpetion.ErrorCode;
import lombok.Getter;

@Getter
public class PostNotOwnerException extends RuntimeException {

  private final ErrorCode errorCode;

  public PostNotOwnerException() {
    super(ErrorCode.POST_NOT_OWNER.getMessage());
    this.errorCode = ErrorCode.POST_NOT_OWNER;
  }
}
