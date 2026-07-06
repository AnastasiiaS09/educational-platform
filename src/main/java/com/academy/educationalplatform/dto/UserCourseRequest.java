package com.academy.educationalplatform.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseRequest {
    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long courseId;
}
