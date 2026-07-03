package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    boolean existsByName(String name);

    Test findByName(String name);

    void deleteByName(String name);
}
