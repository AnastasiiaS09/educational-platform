package com.academy.educationalplatform.service;


import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.ModuleMapper;
import com.academy.educationalplatform.repository.CourseRepository;
import com.academy.educationalplatform.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final ModuleMapper moduleMapper;

    public ModuleService(ModuleRepository moduleRepository, CourseRepository courseRepository, ModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.courseRepository = courseRepository;
        this.moduleMapper = moduleMapper;
    }

    public AnswerRequest addModule(String name, UUID courseId, int lessonNumber, String description) {
        try {

            Module module = new Module();
            module.setCourseId(courseId);
            module.setModuleName(name);
            module.setLessonNumber(lessonNumber);
            module.setDescription(description);

            Module savedModule = moduleRepository.save(module);

            Course course = courseRepository.findById(savedModule.getCourseId()).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND));
            course.setModuleQuantity(course.getModuleQuantity()+1);

            AnswerRequest answer = new AnswerRequest();
            answer.setText("Module was added successfully");

            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }

//    public Module update(UUID id, String name, UUID courseId, int lessonNumber, String description) {
//        try {
//            if (!moduleRepository.existsById(id)) {
//                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
//            }
//
//            Module module = moduleRepository.findById(id).orElseThrow(() ->
//                    PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND));
//            module.setLessonNumber(lessonNumber);
//            module.setDescription(description);
//            module.setName(name);
//            module.setId(courseId);
//
//
//            return module;
//        } catch (RuntimeException e) {
//            throw e;
//        }
//    }

    public Module update(UUID id, ModuleUpdateRequest request) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND));

        moduleMapper.updateModuleFromDto(request,module);

        return moduleRepository.save(module);
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
