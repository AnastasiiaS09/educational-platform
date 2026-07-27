package com.academy.educationalplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.academy.educationalplatform.entity.Module;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {

    Module findByModuleName(String ModuleName);

    void deleteByModuleName(String moduleName);
}
