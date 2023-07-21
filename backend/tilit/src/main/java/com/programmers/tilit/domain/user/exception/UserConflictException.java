package com.programmers.tilit.domain.user.exception;

import com.programmers.tilit.global.common.ErrorCode;
import com.programmers.tilit.global.exception.CustomException;

public class UserConflictException extends CustomException {
    public UserConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
