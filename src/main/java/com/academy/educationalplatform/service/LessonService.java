package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Lesson;
import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson addLesson(String name, UUID courseId, UUID moduleId, String description) {
        try {
            Lesson lesson = new Lesson();
            lesson.setName(name);
            lesson.setDescription(description);
            lesson.setCourseId(courseId);
            lesson.setModuleId(moduleId);

            return lessonRepository.save(lesson);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Lesson update(UUID id, String name, UUID courseId, UUID moduleId, String description) {
        try {
            Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND)
            );
            lesson.setModuleId(moduleId);
            lesson.setDescription(description);
            lesson.setName(name);
            lesson.setCourseId(courseId);


            return lesson;
        } catch (RuntimeException e) {
            throw e;
        }
    }


    public Lesson findById(UUID id) {
        return lessonRepository.findById(id).orElseThrow(() ->
                PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND));
    }


    public void deleteLesson(UUID id) {
        try {
            if (!lessonRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
            }

            lessonRepository.deleteById(id);
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
//
}
