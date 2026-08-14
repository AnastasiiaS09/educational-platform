package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.UserTestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTestAttemptRepository extends JpaRepository<UserTestAttempt, UUID> {
    void deleteAllByUserId(UUID userId);
}
