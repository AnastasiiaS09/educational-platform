package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class LessonUpdateRequest {

    @Size(max = 200)
    private String lessonName;

    private String description;

    private UUID moduleId;

    private Integer lessonNumber;

    private String text;

    @Enumerated(EnumType.STRING)
    private Type type;
}
