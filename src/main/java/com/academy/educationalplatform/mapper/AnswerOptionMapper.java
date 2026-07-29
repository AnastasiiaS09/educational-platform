package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.AnswerOptionRequest;
import com.academy.educationalplatform.entity.AnswerOption;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AnswerOptionMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionId", source = "questionId")
    @Mapping(target = "optionText", source = "optionText")
    @Mapping(target = "optionCorrectness", source = "optionCorrectness")
    AnswerOption toEntity(AnswerOptionRequest request);
}
