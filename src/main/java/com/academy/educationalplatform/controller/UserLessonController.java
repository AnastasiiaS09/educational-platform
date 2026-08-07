package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Status;
import com.academy.educationalplatform.entity.UserCourse;
import com.academy.educationalplatform.entity.UserLesson;
import com.academy.educationalplatform.service.UserCourseService;
import com.academy.educationalplatform.service.UserLessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/userlesson")
public class UserLessonController {
    private final UserLessonService userLessonService;

    public UserLessonController(UserLessonService userLessonService) {
        this.userLessonService = userLessonService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserLessonResponse startLesson(@Valid @RequestBody UserLessonRequest request) {
        var userLesson = userLessonService.startLesson(
                request.getLessonId()
        );
        return toResponse(userLesson);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserLessonResponse> getUsersCompletedLessonsById(@PathVariable UUID userId) {
        return userLessonService.getAllByUserId(userId).stream().filter(userLesson -> userLesson.getStatus().equals(Status.COMPLETED))
                .map(this::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteUserLesson(@PathVariable UUID id) {
        userLessonService.deleteUserLessonById(id);
    }

    @PutMapping
    public void endLesson(@Valid @RequestBody UserLessonEndRequest request) {
        userLessonService.endLesson(
                request.getUserId(),
                request.getLessonId());
    }

    private UserLessonResponse toResponse(UserLesson userLesson) {
        return ApiMapper.toUserLessonResponse(
                userLesson
        );
    }
}
