package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.EnglishTestAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestAttemptAnswerResponse {
    private UUID id;

    private UUID attemptId;

    private UUID questionId;

    private UUID userAnswerId;

    private EnglishTestAnswer correctness;
}
