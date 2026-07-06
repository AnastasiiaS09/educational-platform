package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponse {

    private Long id;

    private Long userId;

    private Role role;
}
