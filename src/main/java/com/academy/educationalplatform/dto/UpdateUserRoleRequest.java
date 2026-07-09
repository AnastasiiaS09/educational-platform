package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRoleRequest {

    @Enumerated(EnumType.STRING)
    private Role role;
}
