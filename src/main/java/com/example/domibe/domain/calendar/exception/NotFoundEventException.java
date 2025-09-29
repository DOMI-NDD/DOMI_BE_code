package com.example.domibe.domain.calendar.exception;

import com.example.domibe.global.excpetion.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundEventException extends RuntimeException {

  private final ErrorCode errorCode;

  public NotFoundEventException() {
    super(ErrorCode.NOT_FOUND_EVENT.getMessage());
    this.errorCode = ErrorCode.NOT_FOUND_EVENT;
  }
}
