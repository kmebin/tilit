package com.programmers.tilit.domain.course.exception;

import com.programmers.tilit.global.common.ErrorCode;
import com.programmers.tilit.global.exception.CustomException;

public class CourseConflictException extends CustomException {
    public CourseConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
