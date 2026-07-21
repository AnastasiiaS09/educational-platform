package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Lesson;
import com.academy.educationalplatform.service.LessonService;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonResponse create(@Valid @RequestBody RegisterLessonRequest request) {
//        Lesson lesson = lessonService.addLesson(
//                request.getLessonName(),
//                request.getModuleId(),
//                request.getDescription(),
//                request.getLessonNumber()
//        );
        return lessonService.addLesson(request);
    }



    @GetMapping("/{id}")
    public LessonResponse getById(@PathVariable UUID id) {
        return toResponse(lessonService.findById(id));
    }

    @GetMapping
    public List<LessonResponse> getAll() {
        return lessonService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

//    @PutMapping("/{id}")
//    public LessonResponse update(@PathVariable UUID id, @Valid @RequestBody LessonUpdateRequest request) {
//        var lesson = lessonService.update(id, request.getLessonName(), request.getModuleId(), request.getDescription(), request.getLessonNumber());
//        return toResponse(lesson);
//    }

    @PutMapping("/{id}")
    public LessonResponse update(@PathVariable UUID id,
                                 @Valid @RequestBody LessonUpdateRequest request) {
        var lesson = lessonService.update(id, request);
        return toResponse(lesson);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        lessonService.deleteLesson(id);
    }

    private LessonResponse toResponse(Lesson lesson) {
        return ApiMapper.toLessonResponse(
                lesson
        );
    }

//
//
//

    @PostMapping(value = "/{id}/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadPoster(@PathVariable UUID id, @RequestParam MultipartFile file) {

        lessonService.uploadVideo(id, file);
    }


    @GetMapping("/{id}/video")
    public ResponseEntity<Resource> getPoster(@PathVariable UUID id) {

        return lessonService.getPoster(id);
    }

    @DeleteMapping("/{id}/video")
    public void deleteVideo(@PathVariable UUID id) {
        lessonService.deleteVideo(id);
    }
}
