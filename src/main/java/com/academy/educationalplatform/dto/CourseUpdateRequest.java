package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateRequest {

    @Size(max = 100)
    private String courseName;
//
    @Size(max = 200)
    private String description;

    private int lectureNumber;
}
