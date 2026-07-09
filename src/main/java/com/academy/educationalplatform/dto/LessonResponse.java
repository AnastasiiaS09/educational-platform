package com.academy.educationalplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {

    private UUID id;

    private String lessonName;

    private String description;

    private UUID courseId;

    private UUID moduleId;
}
