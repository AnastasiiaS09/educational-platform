package com.academy.educationalplatform.service;


import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Module addModule(String name, UUID courseId, int lessonNumber, String description) {
        try {

            Module module = new Module();
            module.setCourseId(courseId);
            module.setName(name);
            module.setLessonNumber(lessonNumber);
            module.setDescription(description);

            return moduleRepository.save(module);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Module update(UUID id, String name, UUID courseId, int lessonNumber, String description) {
        try {
            if (!moduleRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
            }

            Module module = moduleRepository.findById(id).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND));
            module.setLessonNumber(lessonNumber);
            module.setDescription(description);
            module.setName(name);
            module.setId(courseId);


            return module;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Module findById(UUID id) {
        return moduleRepository.findById(id).orElseThrow(() ->
           PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND));
    }

    public void deleteModule(UUID id) {
        try {
            if (!moduleRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND, id);
            }

            moduleRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Module> getAll() {
        try {
            List<Module> moduleList = moduleRepository.findAll();
            return moduleList;

        } catch (RuntimeException e) {
            throw e;
        }
    }
}
