package com.programmers.tilit.domain.course.exception;

import com.programmers.tilit.global.common.ErrorCode;
import com.programmers.tilit.global.exception.CustomException;

public class CourseNotFoundException extends CustomException {
    public CourseNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
