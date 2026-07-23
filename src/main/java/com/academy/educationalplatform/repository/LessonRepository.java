package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Lesson;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    List<Lesson> findAll();

    boolean existsById(UUID id);

    Lesson findByLessonName(String lessonName);

    @Query("""
    FROM Lesson l WHERE l.moduleId = :moduleId
    ORDER BY l.lessonNumber
""")
    List<Lesson> moduleLesson(UUID moduleId);

    void deleteAllByModuleId(UUID moduleId);


    @Modifying
    @Transactional
    @Query("""
    UPDATE Lesson
    SET posterVideo = NULL
    WHERE id = :lessonId
    """)
    void deleteVideoByLessonId(UUID lessonId);

}
