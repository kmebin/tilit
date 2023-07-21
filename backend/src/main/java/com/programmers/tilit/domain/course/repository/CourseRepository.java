package com.programmers.tilit.domain.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmers.tilit.domain.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseCustomRepository {
    Optional<Course> findByName(String name);
}
