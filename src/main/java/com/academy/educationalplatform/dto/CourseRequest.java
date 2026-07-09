package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    @NotBlank
    @Size(max = 200)
    private String courseName;
//
    @Size(max = 200)
    private String description;

    @NotNull
    @Positive
    private int moduleQuantity;
}
