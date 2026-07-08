package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAll();

    boolean existsById(Long id);

    Lesson findByName(String name);
}