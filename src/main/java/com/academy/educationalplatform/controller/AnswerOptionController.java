package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.AnswerOption;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.service.AnswerOptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/answeroptions")
public class AnswerOptionController {
    private final AnswerOptionService answerOptionService;
    public AnswerOptionController(AnswerOptionService answerOptionService) {
        this.answerOptionService = answerOptionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public AnswerRequest addAnswerOption(@Valid @RequestBody AnswerOptionRequest request) {
        return answerOptionService.addAnswerOption(request);
    }

    @GetMapping("/{questionId}/question")
    public List<AnswerOption> getQuestionAnswer(@PathVariable UUID questionId) {
        return answerOptionService.getQuestionAnswer(questionId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAnswerOption(@PathVariable UUID id) {
        answerOptionService.delAnswerOption(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AnswerOptionResponse update(@PathVariable UUID id, @Valid @RequestBody AnswerOptionUpdateRequest request) {
        var answerOption = answerOptionService.update(id, request);
        return toResponse(answerOption);
    }

    private AnswerOptionResponse toResponse(AnswerOption answerOption) {
        return ApiMapper.toAnswerOptionResponse(
                answerOption
        );
    }
}
