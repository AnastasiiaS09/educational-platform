package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.EnglishTest;
import com.academy.educationalplatform.service.EnglishTestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/englishtests")
public class EnglishTestController {
    private final EnglishTestService englishTestService;

    public EnglishTestController(EnglishTestService englishTestService) {
        this.englishTestService = englishTestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnglishTestResponse create(@Valid @RequestBody EnglishTestRequest request) {
        var test = englishTestService.addTest(
                request.getTestName(),
                request.getDescription()
        );
        return toResponse(test);
    }

    @GetMapping("/{id}")
    public EnglishTestResponse getById(@PathVariable UUID id) {
        return toResponse(englishTestService.findById(id));
    }

    @PutMapping("/{id}")
    public EnglishTestResponse update(@PathVariable UUID id, @Valid @RequestBody EnglishTestRequest request) {
        var test = englishTestService.update(id, request.getTestName(), request.getDescription());
        return toResponse(test);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        englishTestService.deleteTest(id);
    }


    private EnglishTestResponse toResponse(EnglishTest englishTest) {
        return ApiMapper.toTestResponse(
                englishTest
        );
    }
}
