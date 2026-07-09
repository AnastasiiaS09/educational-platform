package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID courseId;

    private UUID moduleId;

    private UUID lessonId;

    private UUID userId;

    private String text;

    @CreationTimestamp
    private Instant createdAt;

    @Transient
    private int likeCount;
}