package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.service.EnglishTestQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public AnswerRequest createEnglishTestQuestion(@Valid @RequestBody RegisterEnglishQuestionRequest request) {

        return englishTestQuestionService.createQuestion(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEnglishTestQuestion(@PathVariable UUID id) {
        englishTestQuestionService.deleteQuestion(id);
    }

    private EnglishTestQuestionResponse toResponse(EnglishTestQuestion englishTestQuestion) {
        return ApiMapper.toEnglishTestQuestionResponse(
                englishTestQuestion
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EnglishTestQuestionResponse update(@PathVariable UUID id,
                                 @Valid @RequestBody EnglishTestQuestionUpdateRequest request) {
        var test = englishTestQuestionService.update(id, request);
        return toResponse(test);
    }

    @GetMapping("/{testId}")
    public List<EnglishTestQuestion> getTestQuestion(@PathVariable UUID testId) {
        return englishTestQuestionService.getTestQuestion(testId);
    }
}
