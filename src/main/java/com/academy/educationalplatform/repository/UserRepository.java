package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsById(UUID id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    void deleteById(UUID id);

    User findByEmail(String email);
}
