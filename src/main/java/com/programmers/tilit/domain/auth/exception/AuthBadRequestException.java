package com.programmers.tilit.domain.auth.exception;

import com.programmers.tilit.global.common.ErrorCode;
import com.programmers.tilit.global.exception.CustomException;

public class AuthBadRequestException extends CustomException {
    public AuthBadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
