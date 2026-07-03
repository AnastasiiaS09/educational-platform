package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Lesson;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.LessonRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson addLesson(String name, Long courseId, Long moduleId, String description) {
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

    public Lesson update(Long id, String name, Long courseId, Long moduleId, String description) {
        try {
            Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
            });
            lesson.setModuleId(moduleId);
            lesson.setDescription(description);
            lesson.setName(name);
            lesson.setCourseId(courseId);


            return lesson;
        } catch (RuntimeException e) {
            throw e;
        }
    }


    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
        });
    }


    public void deleteLesson(Long id) {
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
}
