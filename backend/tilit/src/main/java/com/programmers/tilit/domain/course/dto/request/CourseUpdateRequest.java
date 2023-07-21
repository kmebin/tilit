package com.programmers.tilit.domain.course.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CourseUpdateRequest(
    @NotBlank @Size(max = 100) String name,
    @NotBlank String description,
    @NotNull @Min(0) Integer price
) {
}
