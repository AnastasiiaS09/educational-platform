package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonStatusRequest {
    @NotNull
    private UUID lessonId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
