package com.programmers.tilit.global.exception;

import com.programmers.tilit.global.common.ErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode error;

    public CustomException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }
}
