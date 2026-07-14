package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.LessonStatus;
import com.academy.educationalplatform.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LessonStatusRepository extends JpaRepository<LessonStatus, UUID> {
    LessonStatus save(LessonStatus lessonStatus);

    @Query("FROM LessonStatus uc WHERE uc.lessonId = :lessonId")
    List<LessonStatus> findAllByLessonId(UUID lessonId);

    boolean existsByLessonId(UUID lessonId);
    //
    void deleteById(UUID id);

}
