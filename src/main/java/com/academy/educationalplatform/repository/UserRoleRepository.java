package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    UserRole save(UserRole usersRole);

    @Query("FROM UserRole uc WHERE uc.userId = :userId")
    List<UserRole> findAllByUserId(UUID userId);

    boolean existsByUserId(UUID userId);
//
    void deleteById(UUID id);

}
