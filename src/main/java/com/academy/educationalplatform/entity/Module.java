package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String moduleName;

    @NotNull
    private UUID courseId;

    private Integer lessonQuantity;

    private String description;

    private Integer moduleNumber;
}