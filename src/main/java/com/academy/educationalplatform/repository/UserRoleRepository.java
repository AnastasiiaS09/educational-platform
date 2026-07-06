package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.UserCourse;
import com.academy.educationalplatform.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    boolean existsByUserId(Long userId);

    @Query("FROM UserRole uc WHERE uc.userId = :userId")
    List<UserRole> findAllByUserId(Long userId);
}
