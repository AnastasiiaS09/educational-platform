package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
//
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {
    @NotBlank
    private UUID userId;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;
}