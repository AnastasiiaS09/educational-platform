package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.ApiMapper;
import com.academy.educationalplatform.dto.UserCourseRequest;
import com.academy.educationalplatform.dto.UserCourseResponse;
import com.academy.educationalplatform.entity.UserCourse;
import com.academy.educationalplatform.service.UserCourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usercourse")
public class UserCourseController {
    private final UserCourseService userCourseService;

    public UserCourseController(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCourseResponse create(@Valid @RequestBody UserCourseRequest request) {
        var userCourse = userCourseService.joinCourse(
                request.getCourseId()
        );
        return toResponse(userCourse);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserCourseResponse> getUsersCourse() {
        return userCourseService.findUserCourse().stream()
                .map(this::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteUserCourse(@PathVariable UUID id) {
        userCourseService.deleteCourseById(id);
    }

    private UserCourseResponse toResponse(UserCourse userCourse) {
        return ApiMapper.toUserCourseResponse(
                userCourse
        );
    }
}
