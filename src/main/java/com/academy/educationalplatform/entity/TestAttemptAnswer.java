package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "test_attempt_answers")
public class TestAttemptAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID attemptId;

    @NotNull
    private UUID questionId;

    @NotNull
    private UUID userAnswerId;

    @Enumerated(EnumType.STRING)
    private EnglishTestAnswer correctness;
}
