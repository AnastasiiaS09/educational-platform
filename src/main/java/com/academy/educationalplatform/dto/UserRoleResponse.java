package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponse {

    private UUID id;

    private UUID userId;

    private Role role;
}
