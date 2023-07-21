package com.programmers.tilit.domain.user.exception;

import com.programmers.tilit.global.common.ErrorCode;
import com.programmers.tilit.global.exception.CustomException;

public class UserBadRequestException extends CustomException {
    public UserBadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
