package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.UserTestRequest;
import com.academy.educationalplatform.service.UserTestAttemptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/usertest")
public class UserTestAttemptController {
    private final UserTestAttemptService userTestAttemptService;

    public UserTestAttemptController(UserTestAttemptService userTestAttemptService) {
        this.userTestAttemptService = userTestAttemptService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerRequest addUserTest(@Valid @RequestBody UserTestRequest request) {
        return userTestAttemptService.addUserTest(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserTest(@PathVariable UUID id) {
        userTestAttemptService.delUserTest(id);
    }
}
