package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

        @Mapping(target = "userId", source = "userId")
        @Mapping(target = "role", source = "role")
        UserRole toUserRole(UUID userId, Role role);
}
