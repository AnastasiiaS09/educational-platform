package com.academy.educationalplatform.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    private long moduleId;

    @Column(length = 1024)
    private String description;
}
