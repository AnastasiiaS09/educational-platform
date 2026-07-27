package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.UserTestRequest;
import com.academy.educationalplatform.service.UserTestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/usertest")
public class UserTestController {
    private final UserTestService userTestService;

    public UserTestController(UserTestService userTestService) {
        this.userTestService = userTestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerRequest addUserTest(@Valid @RequestBody UserTestRequest request) {
        return userTestService.addUserTest(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserTest(@PathVariable UUID id) {
        userTestService.delUserTest(id);
    }
}
