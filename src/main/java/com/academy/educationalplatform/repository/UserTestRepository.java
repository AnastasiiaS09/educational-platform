package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTestRepository extends JpaRepository<UserTest, UUID> {
    void deleteAllByUserId(UUID userId);
}
