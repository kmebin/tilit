package com.programmers.tilit.domain.course.repository;

import java.util.List;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;

public interface CourseCustomRepository {
    List<Course> findCourses(CourseCategory category, String keyword);
}
