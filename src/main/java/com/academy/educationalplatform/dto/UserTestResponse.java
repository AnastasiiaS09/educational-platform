package com.academy.educationalplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTestResponse {
    private UUID id;

    private UUID userId;

    private UUID testId;

    private int result;

    private double accuracy;
}
