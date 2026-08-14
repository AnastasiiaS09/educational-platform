package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.TestAttemptAnswerRequest;
import com.academy.educationalplatform.entity.TestAttemptAnswer;
import com.academy.educationalplatform.service.TestAttemptAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attemptanswers")
public class TestAttemptAnswerController {
    private final TestAttemptAnswerService testAttemptAnswerService;
    public TestAttemptAnswerController(TestAttemptAnswerService testAttemptAnswerService) {
        this.testAttemptAnswerService = testAttemptAnswerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestAttemptAnswer addTestAttemptAnswer(@Valid @RequestBody TestAttemptAnswerRequest request) {
        return testAttemptAnswerService.addTestAttemptAnswer(request);
    }
}
