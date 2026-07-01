package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "modules")
public class ModuleCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    @Column
    private long courseId;

    @Column(nullable = false)
    private int lessonNumber;

    @Column(length = 1024)
    private String description;
}