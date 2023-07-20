package com.programmers.tilit.global.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {
    READ_COURSES_SUCCESS("강의 목록 조회 성공"),
    READ_COURSE_SUCCESS("강의 상세 조회 성공"),
    CREATE_COURSE_SUCCESS("강의 생성 성공"),
    UPDATE_COURSE_SUCCESS("강의 수정 성공"),
    DELETE_COURSE_SUCCESS("강의 삭제 성공"),
    REGISTER_COURSE_SUCCESS("강의 수강 신청 성공"),

    CREATE_USER_SUCCESS("회원 가입 성공"),
    LOGIN_SUCCESS("로그인 성공");

    private final String value;
}
