package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;


    @Column(nullable = false)
    private int lessonNumber;

    @Column
    private String description;
}
