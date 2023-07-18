package com.programmers.tilit.domain.course.service;

import static com.programmers.tilit.global.common.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.tilit.domain.course.dto.request.CourseCreateRequest;
import com.programmers.tilit.domain.course.dto.response.CourseResponse;
import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.exception.CourseConflictException;
import com.programmers.tilit.domain.course.exception.CourseNotFoundException;
import com.programmers.tilit.domain.course.repository.CourseRepository;
import com.programmers.tilit.domain.user.entity.User;
import com.programmers.tilit.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;

    public CourseResponse getCourse(Long courseId) {
        Course course = findCourseOrThrow(courseId);
        return CourseResponse.from(course);
    }

    @Transactional
    public void createCourse(Long userId, CourseCreateRequest request) {
        validateDuplicate(courseRepository.findByName(request.name()));

        User teacher = userService.findUserOrThrow(userId);
        courseRepository.save(request.toEntity(teacher));
    }

    private Course findCourseOrThrow(Long courseId) {
        return courseRepository.findById(courseId)
            .orElseThrow(() -> new CourseNotFoundException(NO_COURSE));
    }

    private void validateDuplicate(Optional<Course> course) {
        course.ifPresent(c -> {
            throw new CourseConflictException(DUPLICATE_COURSE_NAME);
        });
    }
}
