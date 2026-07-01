package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.ModuleCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<ModuleCourse, Long> {
    ModuleCourse save(ModuleCourse module);

    boolean existsByName(String name);

    ModuleCourse findByName(String name);

    void deleteByName(String name);
}
