package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleResponse {

    private UUID id;

    private String moduleName;

    private String description;

    private int lessonQuantity;

    private UUID courseId;

    private int moduleNumber;

}
