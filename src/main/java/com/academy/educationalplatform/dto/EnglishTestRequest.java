package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.NotBlank;
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
public class EnglishTestRequest {

    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String testName;

    @NotBlank
    private String description;

}
