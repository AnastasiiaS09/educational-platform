package com.academy.educationalplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLessonEndRequest {

    private UUID userId;

    private UUID lessonId;

}
