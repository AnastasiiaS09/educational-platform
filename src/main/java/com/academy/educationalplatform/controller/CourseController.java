package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse create(@Valid @RequestBody CourseRequest request) {
        var course = courseService.addCourse(
                request.getCourseName(),
                request.getModuleQuantity(),
                request.getDescription()
        );
        return toResponse(course);
    }


    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable UUID id) {
        return toResponse(courseService.findById(id));
    }

    @GetMapping
    public List<CourseResponse> getAll() {
        return courseService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable UUID id, @Valid @RequestBody CourseUpdateRequest request) {
        var course = courseService.update(id,request.getModuleQuantity(), request.getDescription(), request.getCourseName());
        return toResponse(course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        courseService.delete(id);
    }

    private CourseResponse toResponse(Course course) {
        return ApiMapper.toCourseResponse(
                course
        );
    }
}