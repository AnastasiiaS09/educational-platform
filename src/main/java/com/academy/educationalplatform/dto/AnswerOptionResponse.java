package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.OptionCorrectness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerOptionResponse {
    private UUID id;

    private UUID questionId;

    private String optionText;

    private boolean isCorrect;
}
