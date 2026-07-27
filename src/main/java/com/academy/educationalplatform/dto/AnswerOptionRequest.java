package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.OptionCorrectness;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class AnswerOptionRequest {

    @NotNull
    private UUID questionId;

    @NotBlank
    private String optionText;

    @Enumerated(EnumType.STRING)
    private OptionCorrectness optionCorrectness;
}
