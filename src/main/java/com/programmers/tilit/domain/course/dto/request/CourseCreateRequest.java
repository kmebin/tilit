package com.programmers.tilit.domain.course.dto.request;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;
import com.programmers.tilit.domain.user.entity.User;

public record CourseCreateRequest(
    CourseCategory category,
    String name,
    String description,
    int price
) {
    public Course toEntity(User teacher) {
        return Course.create(teacher, category, name, description, price);
    }
}
