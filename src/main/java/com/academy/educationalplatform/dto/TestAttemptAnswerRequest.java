package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestAttemptAnswerRequest {
    @NotNull
    private UUID attemptId;

    @NotNull
    private UUID questionId;

    @NotNull
    private UUID userAnswerId;
}
