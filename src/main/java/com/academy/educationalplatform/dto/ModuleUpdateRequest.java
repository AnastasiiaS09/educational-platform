package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
//
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleUpdateRequest {

    @NotBlank
    @Size(max = 200)
    private String moduleName;

    private String description;

    @NotNull
    @PositiveOrZero
    private int lessonNumber;

    private UUID courseId;
}
