package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.LessonStatus;
import com.academy.educationalplatform.entity.Status;
import com.academy.educationalplatform.repository.LessonStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonStatusService {
    private final LessonStatusRepository lessonStatusRepository;

    public LessonStatusService(LessonStatusRepository lessonStatusRepository) {
        this.lessonStatusRepository = lessonStatusRepository;
    }

    public LessonStatus joinStatus(UUID lessonId, Status status) {

        try {
            LessonStatus lessonStatus = new LessonStatus();
            lessonStatus.setLessonId(lessonId);
            lessonStatus.setStatus(status);

            return lessonStatusRepository.save(lessonStatus);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<LessonStatus> findUserRoles(UUID lessonId) {
        return lessonStatusRepository.findAllByLessonId(lessonId);
    }


    public void deleteLessonStatus(UUID id) {
        lessonStatusRepository.deleteById(id);
    }

}
