package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    public Module saveModule(Module module);

    public boolean existsById(Long moduleId);

    public boolean existsByModuleName(String moduleName);

    public Module findModuleById(Long moduleId);

    public Module findModuleByName(String moduleName);

    public void deleteModuleById(Long courseId);
}
