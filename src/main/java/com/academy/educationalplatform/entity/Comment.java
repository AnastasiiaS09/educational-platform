package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long courseId;

    private long moduleId;

    private long lessonId;

    private long userId;

    @Column(nullable = false, length = 512)
    private String text;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Transient
    private int likeCount;
}