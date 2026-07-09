package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {

    boolean existsByName(String name);
//
    void deleteById(UUID id);
}
