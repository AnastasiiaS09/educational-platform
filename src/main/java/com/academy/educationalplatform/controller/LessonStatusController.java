package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.LessonStatus;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.Status;
import com.academy.educationalplatform.entity.UserRole;
import com.academy.educationalplatform.service.LessonStatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/statusses")
public class LessonStatusController {
    private final LessonStatusService lessonStatusService;

    public LessonStatusController(LessonStatusService lessonStatusService) {
        this.lessonStatusService = lessonStatusService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonStatusResponse create(@Valid @RequestBody LessonStatusRequest request) {
        var lessonStatus = lessonStatusService.joinStatus(
                request.getLessonId(),
                request.getStatus()
        );
        return toResponse(lessonStatus);
    }

    @GetMapping("/{lessonId}")
    public List<Status> findStatusByLessonId(@PathVariable UUID lessonId) {
        return lessonStatusService.findUserRoles(lessonId)
                .stream()
                .map(LessonStatus::getStatus)
                .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        lessonStatusService.deleteLessonStatus(id);
    }



    private LessonStatusResponse toResponse(LessonStatus lessonStatus) {
        return ApiMapper.toLessonStatusResponse(
                lessonStatus
        );
    }
}

