package com.programmers.tilit.domain.course.dto.response;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;

import lombok.Builder;

@Builder
public record CourseResponse(
    Long id,
    CourseCategory category,
    String name,
    String description,
    int price,
    int studentCount
) {
    public static CourseResponse from(Course course) {
        return CourseResponse.builder()
            .id(course.getId())
            .category(course.getCategory())
            .name(course.getName())
            .description(course.getDescription())
            .price(course.getPrice())
            .studentCount(course.getStudentCount())
            .build();
    }
}
