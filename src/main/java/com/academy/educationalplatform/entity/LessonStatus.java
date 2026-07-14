package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "lessons_statuses")
public class LessonStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID lessonId;

    @Enumerated(EnumType.STRING)
    private Status status;
}
