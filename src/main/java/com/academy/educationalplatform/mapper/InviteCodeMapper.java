package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.InitiateRequest;
import com.academy.educationalplatform.dto.InviteCodeRequest;
import com.academy.educationalplatform.entity.InviteCode;
import com.academy.educationalplatform.entity.InviteCodeStatus;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InviteCodeMapper {
    InviteCode toInviteCode(String code, InviteCodeStatus status);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInviteCodeFromDto(InitiateRequest dto, @MappingTarget InviteCode inviteCode);
}
