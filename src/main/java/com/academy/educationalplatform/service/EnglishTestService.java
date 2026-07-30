package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.EnglishTestUpdateRequest;
import com.academy.educationalplatform.dto.UpdateUserRequest;
import com.academy.educationalplatform.entity.EnglishTest;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.EnglishTestMapper;
import com.academy.educationalplatform.repository.EnglishTestRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnglishTestService {
    private final EnglishTestRepository englishTestRepository;
    private final EnglishTestMapper englishTestMapper;

    public EnglishTestService(EnglishTestRepository englishTestRepository, EnglishTestMapper englishTestMapper) {
        this.englishTestRepository = englishTestRepository;
        this.englishTestMapper = englishTestMapper;
    }

    public EnglishTest addTest(String name, String description, Integer questionQuantity) {
        try {
            if (englishTestRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.TEST_ALREADY_EXISTS);
            }

            EnglishTest englishTest = new EnglishTest();
            englishTest.setName(name);
            englishTest.setDescription(description);
            englishTest.setQuestionQuantity(questionQuantity);

            return englishTestRepository.save(englishTest);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public EnglishTest findById(UUID id) {
        return englishTestRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND);
        });
    }

//    public EnglishTest update(UUID id, String description, String name) {
//        try {
//            EnglishTest englishTest = englishTestRepository.findById(id).orElseThrow(() ->
//                    PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));
//            englishTest.setDescription(description);
//            englishTest.setName(name);
//
//            return englishTest;
//        } catch (RuntimeException e) {
//            throw e;
//        }
//    }

    public EnglishTest update(UUID id, EnglishTestUpdateRequest request) {
        EnglishTest englishTest = englishTestRepository.findById(id)
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));

        englishTestMapper.updateEnglishTestFromDto(request, englishTest);

        return englishTestRepository.save(englishTest);
    }

    public void deleteTest(UUID id) {
        try {
            englishTestRepository.deleteById(id);
            System.out.println("Test was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}