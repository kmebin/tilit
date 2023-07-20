package com.programmers.tilit.global.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.programmers.tilit.domain.auth.exception.AuthBadRequestException;
import com.programmers.tilit.domain.course.exception.CourseConflictException;
import com.programmers.tilit.domain.course.exception.CourseNotFoundException;
import com.programmers.tilit.domain.user.exception.UserNotFoundException;
import com.programmers.tilit.global.common.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
        AuthBadRequestException.class
    })
    public BaseResponse<Object> handleBadRequestException(CustomException exception) {
        log.error("[BadRequestException] => ", exception);
        return BaseResponse.error(exception.getError());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({
        CourseNotFoundException.class,
        UserNotFoundException.class
    })
    public BaseResponse<Object> handleNotFoundException(CustomException exception) {
        log.error("[NotFoundException] => ", exception);
        return BaseResponse.error(exception.getError());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({
        CourseConflictException.class
    })
    public BaseResponse<Object> handleConflictException(CustomException exception) {
        log.error("[ConflictException] => ", exception);
        return BaseResponse.error(exception.getError());
    }
}
