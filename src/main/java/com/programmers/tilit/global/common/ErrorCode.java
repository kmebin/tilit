package com.programmers.tilit.global.common;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    LOGIN_FAIL(BAD_REQUEST, "이메일 또는 비밀번호가 맞지 않습니다."),
    UNAUTHORIZED_USER(UNAUTHORIZED, "인증되지 않은 요청입니다."),

    NO_COURSE(NOT_FOUND, "존재하지 않는 강의입니다."),
    DUPLICATE_COURSE_NAME(CONFLICT, "이미 존재하는 강의명입니다."),
    CAN_NOT_DELETE_COURSE(CONFLICT, "수강생이 있는 강의는 삭제할 수 없습니다."),
    ALREADY_REGISTER_COURSE(CONFLICT, "이미 수강 중인 강의가 있습니다."),

    NO_USER(NOT_FOUND, "존재하지 않는 유저입니다."),
    DUPLICATE_USER_EMAIL(CONFLICT, "이미 사용 중인 이메일입니다.");

    private final HttpStatus status;
    private final String message;

    public int getStatusCode() {
        return status.value();
    }
}
