package com.programmers.tilit.domain.course.controller;

import static com.programmers.tilit.global.common.SuccessMessage.*;
import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.tilit.domain.course.dto.request.CourseCreateRequest;
import com.programmers.tilit.domain.course.dto.request.CoursesRegisterRequest;
import com.programmers.tilit.domain.course.dto.request.CoursesRequest;
import com.programmers.tilit.domain.course.dto.response.CourseDetailResponse;
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

    @GetMapping
    public BaseResponse<List<CourseResponse>> getCourses(CoursesRequest request) {
        val data = courseService.getCourses(request);
        return BaseResponse.ok(READ_COURSES_SUCCESS, data);
    }

    @GetMapping("/{courseId}")
    public BaseResponse<CourseDetailResponse> getCourse(@PathVariable Long courseId) {
        val data = courseService.getCourse(courseId);
        return BaseResponse.ok(READ_COURSE_SUCCESS, data);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public BaseResponse<Object> createCourse(@RequestBody CourseCreateRequest request) {
        courseService.createCourse(1L, request);
        return BaseResponse.created(CREATE_COURSE_SUCCESS);
    }

    @PatchMapping("/{courseId}")
    public BaseResponse<Object> updateCourse(@PathVariable Long courseId, @RequestBody CourseCreateRequest request) {
        courseService.updateCourse(courseId, request);
        return BaseResponse.ok(UPDATE_COURSE_SUCCESS);
    }

    @DeleteMapping("/{courseId}")
    public BaseResponse<Object> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return BaseResponse.ok(DELETE_COURSE_SUCCESS);
    }

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public BaseResponse<Object> registerCourses(@RequestBody CoursesRegisterRequest request) {
        courseService.registerCourses(1L, request);
        return BaseResponse.created(REGISTER_COURSE_SUCCESS);
    }
}
