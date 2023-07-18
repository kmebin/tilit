package com.programmers.tilit.domain.course.dto.request;

public record CourseUpdateRequest(
    String name,
    String description,
    int price
) {
}
