package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

    @Getter
    @Setter
    @NoArgsConstructor
    @Entity
    @Table(name = "likes")
    public class Like {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "lesson_id", nullable = false)
        private Lesson lesson;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @CreationTimestamp
        @Column(name = "created_at", nullable = false, updatable = false)
        private Instant createdAt;
}
