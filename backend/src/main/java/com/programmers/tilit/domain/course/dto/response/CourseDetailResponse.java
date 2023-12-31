package com.programmers.tilit.domain.course.dto.response;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;

import lombok.Builder;

@Builder
public record CourseDetailResponse(
    Long id,
    CourseCategory category,
    String name,
    Long teacherId,
    String teacherName,
    String description,
    int price,
    int studentCount
) {
    public static CourseDetailResponse from(Course course) {
        return CourseDetailResponse.builder()
            .id(course.getId())
            .category(course.getCategory())
            .name(course.getName())
            .teacherId(course.getTeacher().getId())
            .teacherName(course.getTeacher().getNickname())
            .description(course.getDescription())
            .price(course.getPrice())
            .studentCount(course.getStudentCount())
            .build();
    }
}
