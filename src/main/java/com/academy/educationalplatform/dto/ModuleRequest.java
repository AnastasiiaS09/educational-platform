package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleRequest {

    @NotBlank
    @Size(max = 200)
    private String moduleName;

    private String description;

    @PositiveOrZero
    private int lessonQuantity;

    private UUID courseId;

    @Positive
    private int moduleNumber;

}
