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
    CAN_NOT_DELETE_COURSE(CONFLICT, "수강생이 있는 강의는 삭제할 수 없습니다."),
    ALREADY_REGISTER_COURSE(CONFLICT, "이미 수강 중인 강의가 있습니다."),

    NO_USER(NOT_FOUND, "존재하지 않는 유저입니다.");

    private final HttpStatus status;
    private final String message;

    public int getStatusCode() {
        return status.value();
    }
}
