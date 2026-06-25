package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {

    @NotBlank
    private String testName;

    @NotBlank
    @Size(max = 200)
    private String description;


}
