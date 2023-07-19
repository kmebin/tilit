package com.programmers.tilit.domain.course.service;

import static com.programmers.tilit.global.common.ErrorCode.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.tilit.domain.course.dto.request.CourseCreateRequest;
import com.programmers.tilit.domain.course.dto.request.CoursesRequest;
import com.programmers.tilit.domain.course.dto.response.CourseDetailResponse;
import com.programmers.tilit.domain.course.dto.response.CourseResponse;
import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.exception.CourseConflictException;
import com.programmers.tilit.domain.course.exception.CourseNotFoundException;
import com.programmers.tilit.domain.course.repository.CourseRepository;
import com.programmers.tilit.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;

    public List<CourseResponse> getCourses(CoursesRequest request) {
        val courses = courseRepository.findCourses(request.name(), request.teacher(), request.category());
        return courses.stream()
            .map(CourseResponse::from)
            .toList();
    }

    public CourseDetailResponse getCourse(Long courseId) {
        val course = findCourseOrThrow(courseId);
        return CourseDetailResponse.from(course);
    }

    @Transactional
    public void createCourse(Long userId, CourseCreateRequest request) {
        validateDuplicate(courseRepository.findByName(request.name()));

        val teacher = userService.findUserOrThrow(userId);
        courseRepository.save(request.toEntity(teacher));
    }

    @Transactional
    public void updateCourse(long courseId, CourseCreateRequest request) {
        validateDuplicate(courseRepository.findByName(request.name()), courseId);

        val course = findCourseOrThrow(courseId);
        course.update(request.name(), request.description(), request.price());

        courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(long courseId) {
        val course = findCourseOrThrow(courseId);

        if (course.hasStudents()) {
            throw new CourseConflictException(CAN_NOT_DELETE_COURSE);
        }
        courseRepository.deleteById(courseId);
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

    private void validateDuplicate(Optional<Course> course, Long courseId) {
        course.ifPresent(c -> {
            if (!c.getId().equals(courseId)) {
                throw new CourseConflictException(DUPLICATE_COURSE_NAME);
            }
        });
    }
}
