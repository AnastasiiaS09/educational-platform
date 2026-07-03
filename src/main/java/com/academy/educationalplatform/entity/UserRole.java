package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;
}
