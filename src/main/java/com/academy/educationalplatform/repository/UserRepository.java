package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    void deleteById(Long id);

    User findByEmail(String email);
}
