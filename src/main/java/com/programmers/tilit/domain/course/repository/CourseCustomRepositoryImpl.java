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
import lombok.val;

@Repository
@RequiredArgsConstructor
public class CourseCustomRepositoryImpl implements CourseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Course> findCourses(final String name, final String teacher, final CourseCategory category) {
        val query = jpaQueryFactory.selectFrom(course);

        if (!hasText(teacher)) {
            query.innerJoin(course.teacher, user);
        }
        
        return query.where(
            containsName(name),
            containsTeacher(teacher),
            equalCategory(category)
        ).fetch();
    }

    private BooleanExpression containsName(String name) {
        if (!hasText(name)) {
            return null;
        }
        return course.name.contains(name);
    }

    private BooleanExpression containsTeacher(String teacher) {
        if (!hasText(teacher)) {
            return null;
        }
        return course.teacher.nickname.contains(teacher);
    }

    private BooleanExpression equalCategory(CourseCategory category) {
        if (isNull(category)) {
            return null;
        }
        return course.category.eq(category);
    }
}
