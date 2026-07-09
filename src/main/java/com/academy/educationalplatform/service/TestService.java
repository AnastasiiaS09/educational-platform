package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Test;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public Test findById(UUID id) {
        return testRepository.findById(id).orElseThrow(() ->
                PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND)
        );
    }

    public Test update(UUID id, String description, String name) {
        try {
            Test test = testRepository.findById(id).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));
            test.setDescription(description);
            test.setName(name);

            return test;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteTest(UUID id) {
        try {
            testRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
