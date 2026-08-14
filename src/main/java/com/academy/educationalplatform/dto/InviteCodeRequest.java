package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Availability;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InviteCodeRequest {
    @NotBlank
    @Size(max = 255)
    private String username;

    @NotBlank
    @Size(max = 128)
    private String email;

    @Size(max = 128)
    private String phone;

    @NotBlank
    @Size(max = 255)
    private String code;
    
}
