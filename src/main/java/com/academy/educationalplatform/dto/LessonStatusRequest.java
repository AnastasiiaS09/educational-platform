package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private UUID lessonId;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Status status;
}
