package com.academy.educationalplatform.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String lessonName;

    private UUID moduleId;

    private String description;

    private Integer lessonNumber;

    private String posterVideo;

    @Enumerated(EnumType.STRING)
    private Type type;

}
