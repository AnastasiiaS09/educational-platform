package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.LessonUpdateRequest;
import com.academy.educationalplatform.dto.ModuleUpdateRequest;
import com.academy.educationalplatform.dto.RegisterLessonRequest;
import com.academy.educationalplatform.entity.Lesson;
import com.academy.educationalplatform.entity.Module;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    Lesson toEntity(RegisterLessonRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLessonFromDto(LessonUpdateRequest dto, @MappingTarget Lesson lesson);
}
