package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLessonResponse {
    private UUID id;

    private UUID userId;

    private UUID lessonId;

    private Status status;
}
