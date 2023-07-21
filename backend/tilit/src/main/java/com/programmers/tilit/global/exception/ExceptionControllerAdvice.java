package com.programmers.tilit.global.exception;

import static org.springframework.http.HttpStatus.*;

import java.util.Optional;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.programmers.tilit.domain.auth.exception.AuthBadRequestException;
import com.programmers.tilit.domain.auth.exception.UnauthorizedException;
import com.programmers.tilit.domain.course.exception.CourseConflictException;
import com.programmers.tilit.domain.course.exception.CourseNotFoundException;
import com.programmers.tilit.domain.user.exception.UserBadRequestException;
import com.programmers.tilit.domain.user.exception.UserConflictException;
import com.programmers.tilit.domain.user.exception.UserNotFoundException;
import com.programmers.tilit.global.common.BaseResponse;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
        AuthBadRequestException.class,
        UserBadRequestException.class,
    })
    public BaseResponse<Object> handleBadRequestException(CustomException exception) {
        log.error("[BadRequestException] => ", exception);
        return BaseResponse.error(exception.getError());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Object> handleBadRequestException(MethodArgumentNotValidException exception) {
        log.error("[BadRequestException] => ", exception);

        val errorMessage = Optional.ofNullable(exception.getBindingResult().getFieldError())
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .orElse("입력 값이 올바르지 않습니다.");
        return BaseResponse.error(BAD_REQUEST, errorMessage);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
        IllegalArgumentException.class,
        HttpMessageNotReadableException.class
    })
    public BaseResponse<Object> handleBadRequestException(Exception exception) {
        log.error("[BadRequestException] => ", exception);
        return BaseResponse.error(BAD_REQUEST, exception.getMessage());
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public BaseResponse<Object> handleUnauthorizedException(CustomException exception) {
        log.error("[UnauthorizedException] => ", exception);
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
        CourseConflictException.class,
        UserConflictException.class
    })
    public BaseResponse<Object> handleConflictException(CustomException exception) {
        log.error("[ConflictException] => ", exception);
        return BaseResponse.error(exception.getError());
    }
}
