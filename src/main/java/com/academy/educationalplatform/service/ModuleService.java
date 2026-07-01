package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.ModuleCourse;
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

    public ModuleCourse addModule(String name, long courseId, int lessonNumber, String description) {
        try {
            if (moduleRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_ALREADY_EXISTS, name);
            }

            ModuleCourse module = new ModuleCourse();
            module.setName(name);
            module.setCourseId(courseId);
            module.setLessonNumber(lessonNumber);
            module.setDescription(description);

            return moduleRepository.save(module);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public ModuleCourse updateLessonNumber(String name, int lessonNumber) {
        try {
            if (!moduleRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
            }

            ModuleCourse moduleCourse = moduleRepository.findByName(name);
            moduleCourse.setLessonNumber(lessonNumber);

            return moduleCourse;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public ModuleCourse updateDescription(String name, String description) {
        try {
            if (!moduleRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
            }

            ModuleCourse moduleCourse = moduleRepository.findByName(name);
            moduleCourse.setDescription(description);

            return moduleCourse;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteModule(String name) {
        try {
            if (!moduleRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND, name);
            }

            moduleRepository.deleteByName(name);
            System.out.println("Module was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
