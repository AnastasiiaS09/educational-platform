package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    Course save(Course course);

    boolean existsByName(String name);

    Course findByName(String courseName);
//
    void deleteById(UUID id);

    List<Course> findAll();
}
