package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.ApiMapper;
import com.academy.educationalplatform.dto.EnglishTestQuestionRequest;
import com.academy.educationalplatform.dto.EnglishTestQuestionResponse;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.service.EnglishTestQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/englishtestquestions")
public class EnglishTestQuestionController {
    private final EnglishTestQuestionService englishTestQuestionService;

    public EnglishTestQuestionController(EnglishTestQuestionService englishTestQuestionService) {
        this.englishTestQuestionService = englishTestQuestionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public EnglishTestQuestionResponse createEnglishTestQuestion(@Valid @RequestBody EnglishTestQuestionRequest request) {
        var englishTestQuestion = englishTestQuestionService.createQuestion(
                request.getTestId(),
                request.getQuestionNumber(),
                request.getCorrectAnswer()
        );
        return toResponse(englishTestQuestion);
    }

    private EnglishTestQuestionResponse toResponse(EnglishTestQuestion englishTestQuestion) {
        return ApiMapper.toEnglishTestQuestionResponse(
                englishTestQuestion
        );
    }
}
