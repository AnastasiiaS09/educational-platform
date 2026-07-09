package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.EnglishTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnglishTestRepository extends JpaRepository<EnglishTest, Long> {

    boolean existsByName(String name);

    void deleteById(UUID id);
}