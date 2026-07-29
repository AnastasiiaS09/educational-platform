package com.academy.educationalplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.academy.educationalplatform.entity.Module;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {

    Module findByModuleName(String ModuleName);

    void deleteByModuleName(String moduleName);

    @Query("""
    FROM Module m WHERE m.courseId = :courseId
    ORDER BY m.moduleNumber
""")
    List<Module> courseModule(UUID courseId);
}
