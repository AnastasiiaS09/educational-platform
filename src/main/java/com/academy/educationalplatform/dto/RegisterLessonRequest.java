package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.LessonStatus;
import com.academy.educationalplatform.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterLessonRequest {

    @NotBlank
    @Size(max = 200)
    private String lessonName;

    private String description;

    @NotNull
    private UUID moduleId;

    @PositiveOrZero
    private int lessonNumber;

    private List<Status> statuses;
}
