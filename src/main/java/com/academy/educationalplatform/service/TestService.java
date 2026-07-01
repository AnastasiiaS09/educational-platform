package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Test;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.TestRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test addTest(String name, String description) {
        try {
            if (testRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.TEST_ALREADY_EXISTS);
            }

            Test test = new Test();
            test.setName(name);
            test.setDescription(description);

            return testRepository.save(test);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Test updateDescription(String name, String description) {
        try {
            if (!testRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND);
            }

            Test test = testRepository.findByName(name);
            test.setDescription(description);

            return test;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteTest(String name) {
        try {
            if (!testRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND, name);
            }

            testRepository.deleteByName(name);
            System.out.println("Test was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
