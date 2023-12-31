package com.programmers.tilit.domain.course.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.programmers.tilit.domain.user.entity.User;
import com.programmers.tilit.global.common.TimeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE course SET deleted_at = NOW(), name = NULL WHERE id = ?")
public class Course extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int studentCount;

    @OneToMany(mappedBy = "course")
    private final List<CourseRegistration> courseRegistrations = new ArrayList<>();

    public static Course create(User teacher, CourseCategory category, String name, String description, int price) {
        return Course.builder()
            .teacher(teacher)
            .category(category)
            .name(name)
            .description(description)
            .price(price)
            .build();
    }

    public void update(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public CourseRegistration register(User student) {
        studentCount++;

        val courseRegistration = CourseRegistration.create(this, student);
        courseRegistrations.add(courseRegistration);

        return courseRegistration;
    }

    public boolean hasStudents() {
        return studentCount > 0;
    }

    public boolean isAlreadyRegistered(User student) {
        return courseRegistrations.stream()
            .anyMatch(registration -> registration.getStudent().equals(student));
    }
}
