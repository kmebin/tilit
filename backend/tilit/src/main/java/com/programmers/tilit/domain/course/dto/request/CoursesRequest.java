package com.programmers.tilit.domain.course.dto.request;

import com.programmers.tilit.domain.course.entity.CourseCategory;

public record CoursesRequest(
    String name,
    String teacher,
    CourseCategory category
) {
}
