package com.academy.educationalplatform.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users_modules")
public class UserModule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;

    private UUID moduleId;

    @Enumerated(EnumType.STRING)
    private Status status;
}
