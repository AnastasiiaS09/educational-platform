package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.ModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Module addModule(String name, Long courseId, int lessonNumber, String description) {
        try {

            Module module = new Module();
            module.setName(name);
            module.setLessonNumber(lessonNumber);
            module.setDescription(description);

            return moduleRepository.save(module);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Module update(Long id, String name, Long courseId, int lessonNumber, String description) {
        try {
            if (!moduleRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
            }

            Module module = moduleRepository.findById(id).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
            });
            module.setLessonNumber(lessonNumber);
            module.setDescription(description);
            module.setName(name);
            module.setId(courseId);


            return module;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Module findById(Long id) {
        return moduleRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
        });
    }

    public void deleteModule(Long id) {
        try {
            if (!moduleRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND, id);
            }

            moduleRepository.deleteById(id);
            System.out.println("Module was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
