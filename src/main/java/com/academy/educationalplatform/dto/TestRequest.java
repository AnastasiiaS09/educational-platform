package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {

    @NotBlank
    private String testName;
//
    @NotBlank
    @Size(max = 200)
    private String description;


}
