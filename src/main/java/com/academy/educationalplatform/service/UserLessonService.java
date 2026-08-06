package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Status;
import com.academy.educationalplatform.entity.UserLesson;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserLessonRepository;
import com.academy.educationalplatform.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class UserLessonService {

    private final UserLessonRepository userLessonRepository;

    public UserLessonService(UserLessonRepository userLessonRepository) {
        this.userLessonRepository = userLessonRepository;
    }

    public UserLesson startLesson(UUID lessonId) {

            UUID userId = SecurityUtils.currentUserId();
            if (userLessonRepository.existsByUserIdAndLessonId(userId, lessonId)) {
                return userLessonRepository.findByUserIdAndLessonId(userId, lessonId);
            }


                UserLesson userLesson = new UserLesson();
                userLesson.setUserId(userId);
                userLesson.setLessonId(lessonId);
                userLesson.setStatus(Status.IN_PROGRESS);
                return userLessonRepository.save(userLesson);
    }

    public List<UserLesson> getAllByUserId(UUID userId) {

            if (!SecurityUtils.isAdmin()) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            List<UserLesson> usersLessons = userLessonRepository.findAllByUserId(userId);

            return usersLessons;

    }

    public void deleteUserLessonById(UUID id) {
            UserLesson userLesson = userLessonRepository.findById(id)
                    .orElseThrow(() -> PlatformException.of(PlatformErrorCode.USER_NOT_FOUND));

            if (!userLesson.getUserId().equals(SecurityUtils.currentUserId())
                        || !SecurityUtils.isAdmin()) {
                    throw PlatformException.of(PlatformErrorCode.ACCESS_DENIED);
                }
            userLessonRepository.deleteById(id);

    }

    //user or admin is delete progress
    public void deleteLessonProgress(UUID userId, UUID lessonId) {
            if (!SecurityUtils.currentUser().getId().equals(userId) || !SecurityUtils.isAdmin()) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            userLessonRepository.deleteByUserIdAndLessonId(userId, lessonId);
    }

    public UserLesson endLesson(UUID userId , UUID lessonId) {
        UserLesson userLesson = userLessonRepository.findByUserIdAndLessonId(userId, lessonId);
        userLesson.setStatus(Status.COMPLETED);
        return userLessonRepository.save(userLesson);
    }
}
