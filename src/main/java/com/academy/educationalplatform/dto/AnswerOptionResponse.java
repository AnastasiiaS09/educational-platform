package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.OptionCorrectness;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AnswerOptionResponse {

    private UUID id;

    private UUID questionId;

    private String optionText;

    private OptionCorrectness optionCorrectness;
}
