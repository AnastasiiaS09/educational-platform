package com.academy.educationalplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseResponse {
    private Long id;

    private Long userId;

    private Long courseId;
}
