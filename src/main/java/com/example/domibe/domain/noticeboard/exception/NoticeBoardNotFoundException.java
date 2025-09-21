package com.example.domibe.domain.noticeboard.exception;

import com.example.domibe.global.excpetion.ErrorCode;
import lombok.Getter;

@Getter
public class NoticeBoardNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public NoticeBoardNotFoundException() {
        super(ErrorCode.NOTICE_BOARD_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.NOTICE_BOARD_NOT_FOUND;
    }
}
