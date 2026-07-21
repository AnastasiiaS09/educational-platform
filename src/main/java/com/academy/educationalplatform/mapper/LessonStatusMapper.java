package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.RegisterLessonRequest;
import com.academy.educationalplatform.dto.RegisterUserRequest;
import com.academy.educationalplatform.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LessonStatusMapper {

    @Mapping(target = "lessonId", source = "lessonId")
    @Mapping(target = "status", source = "status")
    LessonStatus toLessonStatus(UUID lessonId, Status status);
}
