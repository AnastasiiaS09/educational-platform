package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    public Course create(String name, String description, int lectureNumber) {
        Course course = new Course();
        return course;
    }

    public Course findById(Long id) {
        Course course = new Course();
        return course;
    }

    public Course update(Long id,String courseName, String description, int lectureNumber) {
        Course course = new Course();
        return course;
    }

    public void delete(Long id) {
    }

    public List<Course> getAll(){
        List<Course> course = new ArrayList<>();
        return course;

    }

}
