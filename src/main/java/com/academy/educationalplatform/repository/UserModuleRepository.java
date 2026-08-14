package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UserLesson;
import com.academy.educationalplatform.entity.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserModuleRepository extends JpaRepository<UserModule, UUID> {

    @Query("FROM UserModule uc WHERE uc.userId = :userId AND uc.moduleId = :moduleId")
    UserModule findByUserIdAndModuleId(
            @Param("userId") UUID userId,
            @Param("moduleId") UUID moduleId
    );

    boolean existsByUserIdAndModuleId(UUID userId, UUID moduleId);

    void deleteAllByUserId(UUID userId);

    @Query("FROM UserModule uc WHERE uc.userId = :userId")
    List<UserModule> findAllByUserId(UUID userId);
}
