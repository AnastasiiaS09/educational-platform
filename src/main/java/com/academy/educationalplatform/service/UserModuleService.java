package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.*;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.LessonRepository;
import com.academy.educationalplatform.repository.ModuleRepository;
import com.academy.educationalplatform.repository.UserLessonRepository;
import com.academy.educationalplatform.repository.UserModuleRepository;
import com.academy.educationalplatform.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserModuleService {
    private final UserLessonRepository userLessonRepository;
    private final LessonRepository lessonRepository;
    private final UserModuleRepository userModuleRepository;

    public UserModuleService(UserLessonRepository userLessonRepository, LessonRepository lessonRepository, UserModuleRepository userModuleRepository) {
        this.userLessonRepository = userLessonRepository;
        this.lessonRepository = lessonRepository;
        this.userModuleRepository = userModuleRepository;
    }

    public UserModule startModule(UUID moduleId) {
            UUID userId = SecurityUtils.currentUserId();

            if (userModuleRepository.existsByUserIdAndModuleId(userId, moduleId)) {
                return userModuleRepository.findByUserIdAndModuleId(userId, moduleId);
            }

            List<UUID> lessonsIdList = lessonRepository.findAllByModuleId(moduleId)
                    .stream()
                    .map(Lesson::getId)
                    .toList();
        List<UUID> userLessonIdList = userLessonRepository.findAllByUserId(userId)
                    .stream()
                    .map(UserLesson::getLessonId)
                    .toList();
            boolean haveAtLeastOnePointOfContact = lessonsIdList.stream()
                    .anyMatch(userLessonIdList::contains);
            if (haveAtLeastOnePointOfContact){

            UserModule userModule = new UserModule();
            userModule.setUserId(userId);
            userModule.setModuleId(moduleId);
            userModule.setStatus(Status.IN_PROGRESS);
            return userModuleRepository.save(userModule);
            } else {
                throw PlatformException.of(PlatformErrorCode.LESSON_NOT_FOUND);
            }
    }

    public UserModule endModule(UUID moduleId) {

        UUID userId = SecurityUtils.currentUserId();

        List<UUID> lessonsIdList = lessonRepository.findAllByModuleId(moduleId)
                .stream()
                .map(Lesson::getId)
                .toList();
        List<UUID> userLessonIdList = userLessonRepository.findAllByUserId(userId)
                .stream()
                .map(UserLesson::getLessonId)
                .toList();

        boolean completed = userLessonIdList.containsAll(lessonsIdList);


        if (!completed) {
            throw PlatformException.of(PlatformErrorCode.INVALID_CREDENTIALS);
        }
        UserModule userModule = userModuleRepository.findByUserIdAndModuleId(userId, moduleId);
        userModule.setStatus(Status.COMPLETED);
        return userModuleRepository.save(userModule);

    }

    public List<UserModule> getAllByUserId(UUID userId) {

        if (!SecurityUtils.isAdmin()) {
            throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
        }

        List<UserModule> usersModules = userModuleRepository.findAllByUserId(userId);

        return usersModules;

    }

    public void deleteUserModuleById(UUID id) {
        UserModule userModule = userModuleRepository.findById(id)
                .orElseThrow(() -> PlatformException.of(PlatformErrorCode.USER_NOT_FOUND));

        if (!userModule.getUserId().equals(SecurityUtils.currentUserId())) {
            throw PlatformException.of(PlatformErrorCode.ACCESS_DENIED);
        }
        userModuleRepository.deleteById(id);

    }
}
