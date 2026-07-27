package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.LessonUpdateRequest;
import com.academy.educationalplatform.dto.RegisterLessonRequest;
import com.academy.educationalplatform.entity.*;
import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.LessonMapper;
import com.academy.educationalplatform.repository.LessonRepository;
import com.academy.educationalplatform.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;
    private final LessonMapper lessonMapper;

    public LessonService(LessonRepository lessonRepository, ModuleRepository moduleRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.moduleRepository = moduleRepository;
        this.lessonMapper = lessonMapper;
    }

    public AnswerRequest addLesson(RegisterLessonRequest request) {
        try {
            Module module = moduleRepository.findById(request.getModuleId()).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND));

            Lesson lesson = lessonMapper.toEntity(request);
            lessonRepository.save(lesson);

            module.setLessonQuantity(module.getLessonQuantity()+1);
            moduleRepository.save(module);

            AnswerRequest answer = new AnswerRequest();
            answer.setText("Lesson was added successfully");

            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }

//    public Lesson update(UUID id, String name, UUID moduleId, String description, int lessonNumber) {
//        try {
//            Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> {
//                throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
//            });
//            lesson.setModuleId(moduleId);
//            lesson.setDescription(description);
//            lesson.setName(name);
//            lesson.setLessonNumber(lessonNumber);
//
//
//            return lesson;
//        } catch (RuntimeException e) {
//            throw e;
//        }
//    }

    public Lesson update(UUID id, LessonUpdateRequest request) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND));

        lessonMapper.updateLessonFromDto(request, lesson);

        return lessonRepository.save(lesson);
    }


    public Lesson findById(UUID id) {
        return lessonRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
        });
    }


    @Transactional
    public AnswerRequest deleteLesson(UUID id) {
        try {
            if (!lessonRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
            }

            Module module = moduleRepository.findById(lessonRepository.findById(id).get().getModuleId()).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
            });

            module.setLessonQuantity(module.getLessonQuantity()-1);

            moduleRepository.save(module);
            lessonRepository.deleteById(id);

            AnswerRequest answer = new AnswerRequest();
            answer.setText("Lesson was deleted successfully");

            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Lesson> getAll() {
        try {
            List<Lesson> lessonList = lessonRepository.findAll();

            return lessonList;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Lesson> getModuleLesson(UUID moduleId) {
        try {
            Module module = moduleRepository.findById(moduleId).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.MODULE_NOT_FOUND);
            });
            List<Lesson> moduleLessonList = lessonRepository.moduleLesson(moduleId);

            return moduleLessonList;
        } catch (RuntimeException e) {
            throw e;
        }
    }


    @Transactional
    public void uploadVideo(UUID lessonId, MultipartFile file) {

        try {
            Lesson lesson = lessonRepository.findById(lessonId)
                    .orElseThrow(() ->
                            PlatformException.of(PlatformErrorCode.POSTER_NOT_FOUND));

            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            Path uploadDir = Paths.get("uploads/posters");

            Files.createDirectories(uploadDir);

            Files.copy(
                    file.getInputStream(),
                    uploadDir.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            lesson.setPosterVideo(fileName);

            lessonRepository.save(lesson);

        } catch (IOException e) {
            throw PlatformException.of(PlatformErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    public ResponseEntity<Resource> getPoster(UUID lessonId) {

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> PlatformException.of(PlatformErrorCode.POSTER_NOT_FOUND));

        Path path = Paths.get("uploads/posters")
                .resolve(lesson.getPosterVideo());

        try {

            Resource resource = new UrlResource(path.toUri());

            String contentType = Files.probeContentType(path);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (IOException e) {

            throw PlatformException.of(PlatformErrorCode.POSTER_NOT_FOUND);
        }
    }

    public void deleteVideo(UUID lessonId) {
        lessonRepository.deleteVideoByLessonId(lessonId);
    }
}
