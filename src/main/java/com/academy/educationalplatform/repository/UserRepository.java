package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    List<User> findAll();

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    User findById(Long id);

    User findByEmail(String email);

    void deleteByEmail(String email);
}
