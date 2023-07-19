package com.programmers.tilit.domain.course.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.programmers.tilit.domain.user.entity.User;
import com.programmers.tilit.global.common.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE course_registration SET deleted_at = NOW() WHERE id = ?")
public class CourseRegistration extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Builder
    private CourseRegistration(Course course, User student) {
        this.course = course;
        this.student = student;
    }

    public static CourseRegistration create(Course course, User student) {
        return CourseRegistration.builder()
            .course(course)
            .student(student)
            .build();
    }
}
