package com.programmers.tilit.domain.course.controller;

import static com.programmers.tilit.global.common.SuccessMessage.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.tilit.domain.course.dto.request.CourseCreateRequest;
import com.programmers.tilit.domain.course.dto.response.CourseResponse;
import com.programmers.tilit.domain.course.service.CourseService;
import com.programmers.tilit.global.common.BaseResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/{courseId}")
    public BaseResponse<CourseResponse> getCourse(@PathVariable long courseId) {
        val data = courseService.getCourse(courseId);
        return BaseResponse.ok(READ_COURSE_SUCCESS, data);
    }

    @PostMapping
    public BaseResponse<Object> createCourse(@RequestBody CourseCreateRequest request) {
        courseService.createCourse(1L, request);
        return BaseResponse.created(CREATE_COURSE_SUCCESS);
    }
}
