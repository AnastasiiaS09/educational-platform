package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.UserCourse;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService {
    private final UserCourseRepository userCourseRepository;

    public UserCourseService(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    public UserCourse joinCourse(Long userId, Long courseId) {

        try {

            UserCourse userCourse = new UserCourse();
            userCourse.setCourseId(courseId);
            userCourse.setUserId(userId);

            return userCourseRepository.save(userCourse);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<UserCourse> findUserCourse(Long userId) {
        try {
            if (!userCourseRepository.existsByUserId(userId)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            return userCourseRepository.findAllByUserId(userId);
        } catch (RuntimeException e) {
            throw e;
        }
    }


}
