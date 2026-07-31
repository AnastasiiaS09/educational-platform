package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.OptionCorrectness;
import com.academy.educationalplatform.entity.Type;
import jakarta.persistence.*;
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
public class AnswerOptionUpdateRequest {

    private UUID questionId;

    private String optionText;

    @Enumerated(EnumType.STRING)
    private OptionCorrectness optionCorrectness;
}