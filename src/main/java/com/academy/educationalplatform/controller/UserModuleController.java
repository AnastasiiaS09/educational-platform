package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Status;
import com.academy.educationalplatform.entity.UserLesson;
import com.academy.educationalplatform.entity.UserModule;
import com.academy.educationalplatform.service.UserLessonService;
import com.academy.educationalplatform.service.UserModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usermodule")
public class UserModuleController {
    private final UserModuleService userModuleService;

    public UserModuleController(UserModuleService userModuleService) {
        this.userModuleService = userModuleService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModuleResponse startModule(@Valid @RequestBody UserModuleRequest request) {
        var userModule = userModuleService.startModule(
                request.getModuleId()
        );
        return toResponse(userModule);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserModuleResponse> getUsersCompletedModulesById(@PathVariable UUID userId) {
        return userModuleService.getAllByUserId(userId).stream().filter(userModule -> userModule.getStatus().equals(Status.COMPLETED))
                .map(this::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteUserModule(@PathVariable UUID id) {
        userModuleService.deleteUserModuleById(id);
    }

    @PutMapping
    public void endModule(@Valid @RequestBody UserModuleRequest request) {
        userModuleService.endModule(
                request.getModuleId()
        );
    }

    private UserModuleResponse toResponse(UserModule userModule) {
        return ApiMapper.toUserModuleResponse(
                userModule
        );
    }
}
