package com.programmers.tilit.domain.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmers.tilit.domain.course.entity.CourseRegistration;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
}
