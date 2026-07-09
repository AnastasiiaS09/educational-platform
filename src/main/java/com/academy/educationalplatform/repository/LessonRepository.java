package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    boolean existsById(UUID id);

    Lesson findByName(String name);
}