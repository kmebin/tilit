package com.programmers.tilit.domain.course.repository;

import java.util.List;
import java.util.Optional;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;

public interface CourseRepository {

    void save(Course course);

    List<Course> findCourses(CourseCategory category, String keyword);

    Course getById(Long id);

    Optional<Course> findByName(String name);

    void deleteById(Long id);
}
