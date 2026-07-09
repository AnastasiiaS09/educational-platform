package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
//
    private String username;

    private String email;

    private String phone;

    private String password;

    private int experiencePoint;

    private int taskCounter;

    private int dayCounter;
}
