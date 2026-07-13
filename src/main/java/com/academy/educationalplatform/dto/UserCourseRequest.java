package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseRequest {

    @NotNull
    @Positive
    private UUID userId;

    @NotNull
    @Positive
    private UUID courseId;
}
