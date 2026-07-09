package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import com.academy.educationalplatform.entity.Module;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {

    boolean existsById(UUID id);
//
    Module findByName(String name);

    void deleteByName(String name);
}
