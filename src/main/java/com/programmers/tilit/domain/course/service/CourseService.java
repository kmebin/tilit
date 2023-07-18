package com.programmers.tilit.domain.course.service;

import static com.programmers.tilit.global.common.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.tilit.domain.course.dto.response.CourseResponse;
import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.exception.CourseNotFoundException;
import com.programmers.tilit.domain.course.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseResponse getCourse(Long courseId) {
        Course course = validateExist(courseRepository.findById(courseId));
        return CourseResponse.from(course);
    }

    private Course validateExist(Optional<Course> course) {
        return course.orElseThrow(() -> new CourseNotFoundException(NO_COURSE));
    }
}
