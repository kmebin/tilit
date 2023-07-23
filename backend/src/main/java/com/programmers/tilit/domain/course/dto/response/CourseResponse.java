package com.programmers.tilit.domain.course.dto.response;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;

import lombok.Builder;

@Builder
public record CourseResponse(
    Long id,
    CourseCategory category,
    String name,
    Long teacherId,
    String teacherName,
    int price
) {
    public static CourseResponse from(Course course) {
        return CourseResponse.builder()
            .id(course.getId())
            .category(course.getCategory())
            .name(course.getName())
            .teacherId(course.getTeacher().getId())
            .teacherName(course.getTeacher().getNickname())
            .price(course.getPrice())
            .build();
    }
}
