package com.academy.educationalplatform.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Getter
@Setter
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
