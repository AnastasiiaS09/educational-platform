package com.academy.educationalplatform.mapper;

import com.academy.educationalplatform.dto.RegisterUserRequest;
import com.academy.educationalplatform.dto.UpdateUserRequest;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UserRole;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "role", constant = "USER")
    User toEntity(RegisterUserRequest request);

    @AfterMapping
    default void applyDefaults(@MappingTarget UserRole role) {
        if (role.getRole() == null) {
            role.setRole(Role.USER);
        }
    }

//    User toEntity(RegisterUserRequest request, UserRole userRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUserRequest dto, @MappingTarget User user);
}
