package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.UserCourse;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCourseService {
    private final UserCourseRepository userCourseRepository;

    public UserCourseService(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    public UserCourse joinCourse(UUID userId, UUID courseId) {

        try {

            UserCourse userCourse = new UserCourse();
            userCourse.setUserId(userId);
            userCourse.setCourseId(courseId);

            return userCourseRepository.save(userCourse);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<UserCourse> findUserCourse(UUID userId) {
        try {
            if (!userCourseRepository.existsByUserId(userId)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            return userCourseRepository.findAllByUserId(userId);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<UserCourse> getAll() {

        try {

            List<UserCourse> users = userCourseRepository.findAll();

            return users;

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteCourseById(UUID id) {
        try {
            if (!userCourseRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            userCourseRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

}
