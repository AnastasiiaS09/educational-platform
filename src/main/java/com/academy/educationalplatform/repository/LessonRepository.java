package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    public Lesson saveCourse(Lesson lesson);

    public boolean existsById(Long lessonId);

    public boolean existsByLessonName(String lessonName);

    public Lesson findLessonById(Long lessonId);

    public Course findLessonByName(String lessonName);

    public void deleteLessonById(Long lessonId);
}
