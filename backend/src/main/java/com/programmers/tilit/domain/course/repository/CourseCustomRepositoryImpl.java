package com.programmers.tilit.domain.course.repository;

import static com.programmers.tilit.domain.course.entity.QCourse.*;
import static com.programmers.tilit.domain.user.entity.QUser.*;
import static java.util.Objects.*;
import static org.springframework.util.StringUtils.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.programmers.tilit.domain.course.entity.Course;
import com.programmers.tilit.domain.course.entity.CourseCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseCustomRepositoryImpl implements CourseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Course> findCourses(final CourseCategory category, final String keyword) {
        return jpaQueryFactory.selectFrom(course)
            .innerJoin(course.teacher, user)
            .where(
                equalCategory(category),
                containsKeyword(keyword)
            ).fetch();
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
