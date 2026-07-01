package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course save(Course course);

    boolean existsByName(String name);

    Course findByName(String courseName);

    void deleteByName(String name);
}
