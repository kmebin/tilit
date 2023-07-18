package com.programmers.tilit.global.common;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    NO_COURSE(NOT_FOUND, "존재하지 않는 강의입니다."),
    DUPLICATE_COURSE_NAME(CONFLICT, "이미 존재하는 강의명입니다."),

    NO_USER(NOT_FOUND, "존재하지 않는 유저입니다.");

    private final HttpStatus status;
    private final String message;

    public int getStatusCode() {
        return status.value();
    }
}
