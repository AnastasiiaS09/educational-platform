package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.CourseUpdateRequest;
import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.CourseMapper;
import com.academy.educationalplatform.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public Course addCourse(String name, int moduleQuantity, String description) {
        try {
            if (courseRepository.existsByCourseName(name)) {
                throw PlatformException.of(PlatformErrorCode.COURSE_ALREADY_EXISTS, name);
            }
            Course course = new Course();
            course.setCourseName(name);
            course.setModuleQuantity(moduleQuantity);
            course.setDescription(description);

            return courseRepository.save(course);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Course findById(UUID id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND);
        });

    }

//    public Course update(UUID id, int moduleQuantity, String description, String name) {
//        try {
//            Course course = courseRepository.findById(id).orElseThrow(() -> {
//                throw PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND);
//            });
//
//            course.setModuleQuantity(moduleQuantity);
//            course.setDescription(description);
//            course.setName(name);
//
//            return course;
//        } catch (RuntimeException e) {
//            throw e;
//        }
//    }

    public Course update(UUID id, CourseUpdateRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND));

        courseMapper.updateCourseFromDto(request,course);

        return courseRepository.save(course);
    }

    public void delete(UUID id) {
        try {
            courseRepository.deleteById(id);
            System.out.println("Test was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }


    public List<Course> getAll() {
        try {
            List<Course> courseList = courseRepository.findAll();
            return courseList;

        } catch (RuntimeException e) {
            throw e;
        }
    }
}
