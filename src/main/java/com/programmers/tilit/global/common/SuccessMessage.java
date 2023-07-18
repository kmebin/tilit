package com.programmers.tilit.global.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {
    READ_COURSE_SUCCESS("강의 상세 조회 성공");

    private final String value;
}
