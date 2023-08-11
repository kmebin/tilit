package com.programmers.tilit.domain.course.repository;

import static com.programmers.tilit.domain.course.entity.QCourse.*;
import static com.programmers.tilit.domain.user.entity.QUser.*;
import static com.programmers.tilit.global.common.ErrorCode.*;
import static java.util.Objects.*;
import static org.springframework.util.StringUtils.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;
import com.programmers.tilit.domain.course.exception.CourseNotFoundException;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseJpaRepository courseJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Course course) {
        courseJpaRepository.save(course);
    }

    @Override
    public List<Course> findCourses(final CourseCategory category, final String keyword) {
        return jpaQueryFactory.selectFrom(course)
            .innerJoin(course.teacher, user)
            .where(
                equalCategory(category),
                containsKeyword(keyword)
            ).fetch();
    }

    @Override
    public Course getById(Long id) {
        return courseJpaRepository.findById(id)
            .orElseThrow(() -> new CourseNotFoundException(NO_COURSE));
    }

    @Override
    public Optional<Course> findByName(String name) {
        return courseJpaRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        courseJpaRepository.deleteById(id);
    }

    private BooleanExpression equalCategory(CourseCategory category) {
        if (isNull(category)) {
            return null;
        }
        return course.category.eq(category);
    }

    private BooleanExpression containsKeyword(String keyword) {
        if (!hasText(keyword)) {
            return null;
        }
        return course.name.contains(keyword).or(course.teacher.nickname.contains(keyword));
    }
}
