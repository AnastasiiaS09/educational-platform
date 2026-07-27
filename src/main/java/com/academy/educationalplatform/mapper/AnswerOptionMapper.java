package com.academy.educationalplatform.mapper;


import com.academy.educationalplatform.dto.AnswerOptionUpdateRequest;
import com.academy.educationalplatform.entity.AnswerOption;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AnswerOptionMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAnswerOptionFromDto(AnswerOptionUpdateRequest dto, @MappingTarget AnswerOption answerOption);
}
