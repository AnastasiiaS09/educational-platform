package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourse, UUID> {

    boolean existsByUserId(UUID userId);

    @Query("FROM UserCourse uc WHERE uc.userId = :userId")
    List<UserCourse> findAllByUserId(UUID userId);

    @Query("FROM UserCourse uc WHERE uc.courseId = :courseId")
    List<UserCourse> findAllByCourseId(UUID courseId);

    void deleteAllByUserId(UUID userId);

    boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
}
