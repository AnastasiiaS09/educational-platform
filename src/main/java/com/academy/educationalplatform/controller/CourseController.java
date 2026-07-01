package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse create(@Valid @RequestBody CourseRequest request) {
        var course = courseService.create(
                request.getCourseName(),
                request.getDescription(),
                request.getLectureNumber()
        );
        return toResponse(course);
    }


    @GetMapping("/{id}")
    public CourseResponse get(@PathVariable Long id) {
        return toResponse(courseService.findById(id));
    }

    @GetMapping
    public List<CourseResponse> list() {
        return courseService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id, @Valid @RequestBody CourseUpdateRequest request) {
        var course = courseService.update(id, request.getCourseName(), request.getDescription(), request.getLectureNumber());
        return toResponse(course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }

    private CourseResponse toResponse(Course course) {
        return ApiMapper.toCourseResponse(
                course
        );
    }
}