package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.service.ModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModuleResponse create(@Valid @RequestBody ModuleRequest request) {
        var module = moduleService.addModule(
                request.getModuleName(),
                request.getCourseId(),
                request.getLessonNumber(),
                request.getDescription()
        );
        return toResponse(module);
    }


    @GetMapping("/{id}")
    public ModuleResponse getById(@PathVariable UUID id) {
        return toResponse(moduleService.findById(id));
    }

    @GetMapping
    public List<ModuleResponse> getAll() {
        return moduleService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public ModuleResponse update(@PathVariable UUID id, @Valid @RequestBody ModuleUpdateRequest request) {
        var module = moduleService.update(id, request.getModuleName(), request.getCourseId(), request.getLessonNumber(), request.getDescription());
        return toResponse(module);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
       moduleService.deleteModule(id);
    }

    private ModuleResponse toResponse(Module module) {
        return ApiMapper.toModuleResponse(
                module
        );
    }
}
