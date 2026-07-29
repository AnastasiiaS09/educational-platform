package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.service.ModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {
    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public AnswerRequest create(@Valid @RequestBody ModuleRequest request) {
        return moduleService.addModule(request);
    }


    @GetMapping("/{id}")
    public ModuleResponse getById(@PathVariable UUID id) {
        return toResponse(moduleService.findById(id));
    }

    @GetMapping("/{courseId}/course")
    public List<ModuleResponse> getCourseModule(@PathVariable UUID courseId) {
        return moduleService.getCourseModule(courseId).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping
    public List<ModuleResponse> getAll() {
        return moduleService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }
//
//    @PutMapping("/{id}")
//    public ModuleResponse update(@PathVariable UUID id, @Valid @RequestBody ModuleUpdateRequest request) {
//        var module = moduleService.update(id, request.getModuleName(), request.getCourseId(), request.getLessonNumber(), request.getDescription());
//        return toResponse(module);
//    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModuleResponse update(@PathVariable UUID id, @Valid @RequestBody ModuleUpdateRequest request) {
        var module = moduleService.update(id, request);
        return toResponse(module);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
       moduleService.deleteModule(id);
    }

    private ModuleResponse toResponse(Module module) {
        return ApiMapper.toModuleResponse(
                module
        );
    }
}
