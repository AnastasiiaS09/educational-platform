package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.CourseUpdateRequest;
import com.academy.educationalplatform.dto.ModuleUpdateRequest;
import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.Module;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModuleFromDto(ModuleUpdateRequest dto, @MappingTarget Module module);
}
