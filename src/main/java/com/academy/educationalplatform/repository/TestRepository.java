package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository  extends JpaRepository<Test, Long> {

    public Test saveTest(Test test);

    public boolean existsById(Long testId);

    public boolean existsByTestName(String testName);

    public Test findTestById(Long testId);

    public Test findTestByName(String testName);

    public void deleteTestById(Long testId);
}
