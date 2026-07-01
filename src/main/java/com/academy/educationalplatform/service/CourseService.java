package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, int lectureNumber, String description) {
        try {
            if (courseRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.COURSE_ALREADY_EXISTS, name);
            }
            Course course = new Course();
            course.setName(name);
            course.setLectureNumber(lectureNumber);
            course.setDescription(description);

            return courseRepository.save(course);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Course updateLectureNumber(String name, int lectureNumber) {
        try {
            if (!courseRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND);
            }

            Course course = courseRepository.findByName(name);
            course.setLectureNumber(lectureNumber);

            return course;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Course updateDescription(String name, String description) {
        try {
            if (!courseRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND);
            }

            Course course = courseRepository.findByName(name);
            course.setDescription(description);

            return course;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteCourse(String name) {
        try {
            if (!courseRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND, name);
            }

            courseRepository.deleteByName(name);
            System.out.println("Test was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
