package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    boolean existsByUserId(Long userId);

    @Query("FROM UserCourse uc WHERE uc.userId = :userId")
    List<UserCourse> findAllByUserId(Long userId);
}
