package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tests_questions")
public class EnglishTestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID testId;

    private int questionNumber;

    private String correctAnswer;
}
