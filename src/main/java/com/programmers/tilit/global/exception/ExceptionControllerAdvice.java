package com.programmers.tilit.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.programmers.tilit.domain.course.exception.CourseNotFoundException;
import com.programmers.tilit.global.common.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
        CourseNotFoundException.class
    })
    public BaseResponse<Object> handleNotFoundException(CustomException exception) {
        log.error("[NotFoundException] => ", exception);
        return BaseResponse.error(exception.getError());
    }
}
