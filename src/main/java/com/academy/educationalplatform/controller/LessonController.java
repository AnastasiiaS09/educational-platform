package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Lesson;
import com.academy.educationalplatform.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonResponse create(@Valid @RequestBody LessonRequest request) {
        var lesson = lessonService.addLesson(
                request.getLessonName(),
                request.getCourseId(),
                request.getModuleId(),
                request.getDescription()
        );
        return toResponse(lesson);
    }


    @GetMapping("/{id}")
    public LessonResponse getById(@PathVariable Long id) {
        return toResponse(lessonService.findById(id));
    }

    @GetMapping
    public List<LessonResponse> getAll() {
        return lessonService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public LessonResponse update(@PathVariable Long id, @Valid @RequestBody LessonUpdateRequest request) {
        var lesson = lessonService.update(id, request.getLessonName(), request.getCourseId(), request.getModuleId(), request.getDescription());
        return toResponse(lesson);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    private LessonResponse toResponse(Lesson lesson) {
        return ApiMapper.toLessonResponse(
                lesson
        );
    }
}
