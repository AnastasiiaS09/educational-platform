package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.*;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.LessonMapper;
import com.academy.educationalplatform.mapper.LessonStatusMapper;
import com.academy.educationalplatform.repository.LessonRepository;
import com.academy.educationalplatform.repository.LessonStatusRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final LessonStatusRepository lessonStatusRepository;
    private final LessonStatusMapper lessonStatusMapper;

    public LessonService(LessonRepository lessonRepository, LessonMapper lessonMapper, LessonStatusRepository lessonStatusRepository, LessonStatusMapper lessonStatusMapper) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.lessonStatusRepository = lessonStatusRepository;
        this.lessonStatusMapper = lessonStatusMapper;
    }

    public LessonResponse addLesson(RegisterLessonRequest request) {
        try {
            Lesson lesson = lessonMapper.toEntity(request);
            lessonRepository.save(lesson);

            List<Status> statuses = request.getStatuses();

            if (statuses == null || statuses.isEmpty()) {
                statuses = List.of(Status.TEXT);
            }

            for (Status status : statuses) {
                lessonStatusRepository.save(
                        lessonStatusMapper.toLessonStatus(lesson.getId(), status)
                );
            }

            LessonResponse lessonResponse = new LessonResponse();
            lessonResponse.setId(lesson.getId());
            lessonResponse.setLessonName(lesson.getLessonName());
            lessonResponse.setDescription(lesson.getDescription());
            lessonResponse.setModuleId(lesson.getModuleId());
            lessonResponse.setLessonNumber(lesson.getLessonNumber());

            return lessonResponse;
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

       lessonMapper.updateLessonFromDto(request,lesson);

        return lessonRepository.save(lesson);
    }


    public Lesson findById(UUID id) {
        return lessonRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
        });
    }


    public void deleteLesson(UUID id) {
        try {
            if (!lessonRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
            }

            lessonRepository.deleteById(id);
            System.out.println("Lesson was deleted successfully");
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
        try {

            lessonRepository.deleteVideoByLessonId(lessonId);
        } catch (Exception e) {
            throw e;
        }
    }
}
