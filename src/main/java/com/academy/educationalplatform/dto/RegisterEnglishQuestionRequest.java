package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEnglishQuestionRequest {
    @NotNull
    private UUID testId;

    @NotBlank
    private String questionText;

    @NotNull
    private int questionNumber;

    @NotBlank
    @Size(max = 255)
    private String correctAnswer;
}
