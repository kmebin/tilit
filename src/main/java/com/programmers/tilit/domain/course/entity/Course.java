package com.programmers.tilit.domain.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
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
@SQLDelete(sql = "UPDATE course SET deleted_at = NOW() WHERE id = ?")
public class Course extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @ColumnDefault("0")
    @Column(name = "student_count", nullable = false)
    private int studentCount;

    @Builder
    private Course(User teacher, CourseCategory category, String name, String description, int price) {
        this.teacher = teacher;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Course create(User teacher, CourseCategory category, String name, String description, int price) {
        return new Course(teacher, category, name, description, price);
    }

    public void update(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public boolean hasStudents() {
        return studentCount > 0;
    }
}
