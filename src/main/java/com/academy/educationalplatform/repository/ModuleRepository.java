package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import com.academy.educationalplatform.entity.Module;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> getAll();

    boolean existsById(Long id);

    Module findByName(String name);

    void deleteByName(String name);
}
