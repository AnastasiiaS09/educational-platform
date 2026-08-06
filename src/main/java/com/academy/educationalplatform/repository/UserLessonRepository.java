package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.UserCourse;
import com.academy.educationalplatform.entity.UserLesson;
import com.academy.educationalplatform.entity.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserLessonRepository extends JpaRepository<UserLesson, UUID> {

    boolean existsByUserIdAndLessonId(UUID userId, UUID lessonId);

    @Query("FROM UserLesson uc WHERE uc.userId = :userId")
    List<UserLesson> findAllByUserId(UUID userId);

    void deleteByUserIdAndLessonId(UUID userId, UUID lessonId);

    @Query("FROM UserLesson ul WHERE ul.userId = :userId AND ul.lessonId = :lessonId")
    UserLesson findByUserIdAndLessonId(
            @Param("userId") UUID userId,
            @Param("lessonId") UUID lessonId);


}
