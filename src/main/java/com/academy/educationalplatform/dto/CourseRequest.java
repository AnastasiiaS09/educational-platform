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
public class CourseRequest {

    private UUID id;

    @NotBlank
    @Size(max = 200)
    private String courseName;

    @Size(max = 200)
    private String description;

    @NotNull
    @Positive
    private int moduleQuantity;
}
