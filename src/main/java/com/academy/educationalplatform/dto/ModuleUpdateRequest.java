package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleUpdateRequest {

    @NotBlank
    @Size(max = 200)
    private String moduleName;

    @Size(max = 200)
    private String description;

    @NotNull
    @Positive
    private int lessonNumber;

    @NotNull
    @Positive
    private Long courseId;
}
