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
public class LessonRequest {

    @NotBlank
    @Size(max = 200)
    private String lessonName;

    private String description;

    @NotNull
    private UUID moduleId;

    @PositiveOrZero
    private int lessonNumber;

}
