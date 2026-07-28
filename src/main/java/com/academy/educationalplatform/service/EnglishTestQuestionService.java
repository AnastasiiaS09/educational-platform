package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.EnglishTestQuestionRequest;
import com.academy.educationalplatform.dto.RegisterEnglishQuestionRequest;
import com.academy.educationalplatform.entity.EnglishTest;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.EnglishTestQuestionMapper;
import com.academy.educationalplatform.repository.EnglishTestQuestionRepository;
import com.academy.educationalplatform.repository.EnglishTestRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnglishTestQuestionService {
    private final EnglishTestQuestionRepository englishTestQuestionRepository;
    private final EnglishTestQuestionMapper englishTestQuestionMapper;
    private final EnglishTestRepository englishTestRepository;

    public EnglishTestQuestionService(EnglishTestQuestionRepository englishTestQuestionRepository, EnglishTestQuestionMapper englishTestQuestionMapper, EnglishTestRepository englishTestRepository) {
        this.englishTestQuestionRepository = englishTestQuestionRepository;
        this.englishTestQuestionMapper = englishTestQuestionMapper;
        this.englishTestRepository = englishTestRepository;
    }

    public AnswerRequest createQuestion(RegisterEnglishQuestionRequest request) {
        try {
            if (englishTestQuestionRepository.existsByQuestionNumber(request.getQuestionNumber())) {
                throw PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_ALREADY_EXISTS);
            }

            EnglishTestQuestion englishTestQuestion = englishTestQuestionMapper.toEntity(request);
            englishTestQuestionRepository.save(englishTestQuestion);

            EnglishTest englishTest = englishTestRepository.findById(englishTestQuestion.getTestId()).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));
            englishTest.setQuestionQuantity(englishTest.getQuestionQuantity()+1);

            englishTestRepository.save(englishTest);

            AnswerRequest answer = new AnswerRequest();
            answer.setText("Question was added successfully");

            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteQuestion(UUID id) {
        try {
            if(!englishTestQuestionRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_NOT_FOUND);
            }

            EnglishTest englishTest = englishTestRepository.findById(englishTestQuestionRepository.findById(id).get().getTestId()).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));

            englishTest.setQuestionQuantity(englishTest.getQuestionQuantity()-1);
            englishTestQuestionRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
