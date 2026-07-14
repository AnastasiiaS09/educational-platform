package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "english_test_results")
public class UserEnglishTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID questionId;

    private UUID userId;

    private String userAnswer;

    private EnglishTestAnswer englishTestAnswer;

    @Enumerated(EnumType.STRING)
    private EnglishTestAnswer correctness;
}
