package com.academy.educationalplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnglishTestQuestionResponse {
    private UUID id;

    private UUID testId;

    private String questionText;

    private Integer questionNumber;
}
