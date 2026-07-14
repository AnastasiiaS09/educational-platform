package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonStatusResponse {

    private UUID id;

    private UUID lessonId;

    private Status status;
}
