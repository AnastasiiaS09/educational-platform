package com.academy.educationalplatform.mapper;


import com.academy.educationalplatform.dto.EnglishTestUpdateRequest;
import com.academy.educationalplatform.entity.EnglishTest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EnglishTestMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEnglishTestFromDto(EnglishTestUpdateRequest dto, @MappingTarget EnglishTest englishTest);

}
