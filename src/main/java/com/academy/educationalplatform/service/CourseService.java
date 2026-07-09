package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, int moduleQuantity, String description) {
        try {
            if (courseRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.COURSE_ALREADY_EXISTS, name);
            }
            Course course = new Course();
            course.setName(name);
            course.setModuleQuantity(moduleQuantity);
            course.setDescription(description);

            return courseRepository.save(course);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND);
        });

    }

    public Course update(Long id, int moduleQuantity, String description, String name) {
        try {
            Course course = courseRepository.findById(id).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.COURSE_NOT_FOUND);
            });

            course.setModuleQuantity(moduleQuantity);
            course.setDescription(description);
            course.setName(name);

            return course;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void delete(Long id) {
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
