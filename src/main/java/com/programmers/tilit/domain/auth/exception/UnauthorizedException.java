package com.programmers.tilit.domain.auth.exception;

import com.programmers.tilit.global.common.ErrorCode;
import com.programmers.tilit.global.exception.CustomException;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
