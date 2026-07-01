package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public Course saveCourse(Course course);

    public boolean existsById(Long courseId);

    public boolean existsByCourseName(String courseName);

    public Course findCourseById(Long courseId);

    public Course findCourseByName(String courseName);

    public void deleteCourseById(Long courseId);
}
