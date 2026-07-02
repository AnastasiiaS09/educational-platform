package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Lesson;
import com.academy.educationalplatform.entity.ModuleCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    boolean existsById(Long id);

    Lesson findByName(String name);
}