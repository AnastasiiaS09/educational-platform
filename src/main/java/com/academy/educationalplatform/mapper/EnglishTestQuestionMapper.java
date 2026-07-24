package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.EnglishTestQuestionRequest;
import com.academy.educationalplatform.dto.RegisterEnglishQuestionRequest;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnglishTestQuestionMapper {
    EnglishTestQuestion toEntity(RegisterEnglishQuestionRequest request);
}
