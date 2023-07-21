package com.programmers.tilit.domain.course.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

public record CoursesRegisterRequest(
    @NotNull
    List<Long> courseIds
) {
}
