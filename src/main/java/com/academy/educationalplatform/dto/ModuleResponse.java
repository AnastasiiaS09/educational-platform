package com.academy.educationalplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleResponse {

    private Long id;

    private String moduleName;

    private String description;

    private int lessonNumbers;

    private Long courseId;

}
