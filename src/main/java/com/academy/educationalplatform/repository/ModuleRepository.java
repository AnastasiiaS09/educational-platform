package com.academy.educationalplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.academy.educationalplatform.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    boolean existsById(Long id);

    Module findByName(String name);

    void deleteByName(String name);
}
