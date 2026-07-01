package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_courses")
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;

    private long courseId;
}
