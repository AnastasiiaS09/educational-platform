package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.UserCourse;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserCourseRepository;
import com.academy.educationalplatform.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCourseService {
    private final UserCourseRepository userCourseRepository;

    public UserCourseService(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    public UserCourse joinCourse(UUID courseId) {

        try {
            UUID userId = SecurityUtils.currentUserId();
            if (userCourseRepository.existsByUserIdAndCourseId(userId, courseId)) {
                throw PlatformException.of(PlatformErrorCode.COURSE_ALREADY_EXISTS);
            }
            else {


                UserCourse userCourse = new UserCourse();
                userCourse.setUserId(userId);
                userCourse.setCourseId(courseId);
            return userCourseRepository.save(userCourse);
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<UserCourse> findUserCourse() {
        try {
            UUID userId = SecurityUtils.currentUserId();
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
            if (!SecurityUtils.isAdmin()) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            List<UserCourse> users = userCourseRepository.findAll();

            return users;

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteUserCourseById(UUID id) {
        try {
            if (!userCourseRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            } if (!SecurityUtils.currentUserId().equals(id) || !SecurityUtils.isAdmin()) {
                throw PlatformException.of(PlatformErrorCode.ACCESS_DENIED);
            }

            userCourseRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    //user is not registered anymore (?realization)
    public void deleteCourse(UUID userId) {
        try {
            if (!userCourseRepository.existsByUserId(userId)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            } if (!SecurityUtils.currentUser().getId().equals(userId)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            userCourseRepository.deleteById(userId);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
