package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Test;
import com.academy.educationalplatform.service.TestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestResponse create(@Valid @RequestBody TestRequest request) {
        var test = testService.addTest(
                request.getTestName(),
                request.getDescription()
        );
        return toResponse(test);
    }

    @GetMapping("/{id}")
    public TestResponse getById(@PathVariable UUID id) {
        return toResponse(testService.findById(id));
    }

    @PutMapping("/{id}")
    public TestResponse update(@PathVariable UUID id, @Valid @RequestBody TestRequest request) {
        var test = testService.update(id, request.getTestName(), request.getDescription());
        return toResponse(test);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        testService.deleteTest(id);
    }


    private TestResponse toResponse(Test test) {
        return ApiMapper.toTestResponse(
                test
        );
    }
}
