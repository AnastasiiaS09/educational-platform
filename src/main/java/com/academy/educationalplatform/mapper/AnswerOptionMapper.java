package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.AnswerOptionRequest;
import com.academy.educationalplatform.dto.AnswerOptionUpdateRequest;
import com.academy.educationalplatform.dto.EnglishTestQuestionUpdateRequest;
import com.academy.educationalplatform.entity.AnswerOption;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AnswerOptionMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionId", source = "questionId")
    @Mapping(target = "optionText", source = "optionText")
    @Mapping(target = "optionCorrectness", source = "optionCorrectness")
    AnswerOption toEntity(AnswerOptionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAnswerOptionFromDto(AnswerOptionUpdateRequest dto, @MappingTarget AnswerOption answerOption);

}
