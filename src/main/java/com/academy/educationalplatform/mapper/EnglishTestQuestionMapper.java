package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.EnglishTestQuestionRequest;
import com.academy.educationalplatform.dto.EnglishTestQuestionUpdateRequest;
import com.academy.educationalplatform.dto.LessonUpdateRequest;
import com.academy.educationalplatform.dto.RegisterEnglishQuestionRequest;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.entity.Lesson;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EnglishTestQuestionMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EnglishTestQuestion toEntity(RegisterEnglishQuestionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTestQuestionFromDto(EnglishTestQuestionUpdateRequest dto, @MappingTarget EnglishTestQuestion englishTestQuestion);
}
