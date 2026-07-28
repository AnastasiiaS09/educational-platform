package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.EnglishTestQuestionRequest;
import com.academy.educationalplatform.dto.RegisterEnglishQuestionRequest;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EnglishTestQuestionMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EnglishTestQuestion toEntity(RegisterEnglishQuestionRequest request);
}
