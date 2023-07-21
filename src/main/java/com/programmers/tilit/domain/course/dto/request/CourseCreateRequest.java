package com.programmers.tilit.domain.course.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;
import com.programmers.tilit.domain.user.entity.User;

public record CourseCreateRequest(
    @NotNull
    CourseCategory category,

    @NotBlank
    @Size(max = 100)
    String name,

    @NotBlank
    String description,

    @Min(0)
    @NotNull
    Integer price
) {
    public Course toEntity(User teacher) {
        return Course.create(teacher, category, name, description, price);
    }
}
