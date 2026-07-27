package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "answer_options")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID questionId;

    @NotBlank
    private String optionText;

    @Enumerated(EnumType.STRING)
    private OptionCorrectness optionCorrectness;
}
