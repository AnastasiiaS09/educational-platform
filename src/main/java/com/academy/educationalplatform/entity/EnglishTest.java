package com.academy.educationalplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tests")
public class EnglishTest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String testName;

    private String description;

    private Integer maxScore;

    private Integer questionQuantity;
}