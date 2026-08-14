package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.RegisterEnglishQuestionRequest;
import com.academy.educationalplatform.entity.EnglishTest;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.EnglishTestQuestionMapper;
import com.academy.educationalplatform.repository.AnswerOptionRepository;
import com.academy.educationalplatform.repository.EnglishTestQuestionRepository;
import com.academy.educationalplatform.repository.EnglishTestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EnglishTestQuestionService {
    private final EnglishTestQuestionRepository englishTestQuestionRepository;
    private final EnglishTestQuestionMapper englishTestQuestionMapper;
    private final EnglishTestRepository englishTestRepository;
    private final AnswerOptionRepository answerOptionRepository;

    public EnglishTestQuestionService(EnglishTestQuestionRepository englishTestQuestionRepository, EnglishTestQuestionMapper englishTestQuestionMapper, EnglishTestRepository englishTestRepository, AnswerOptionRepository answerOptionRepository) {
        this.englishTestQuestionRepository = englishTestQuestionRepository;
        this.englishTestQuestionMapper = englishTestQuestionMapper;
        this.englishTestRepository = englishTestRepository;
        this.answerOptionRepository = answerOptionRepository;
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
            englishTest.setMaxScore(englishTest.getMaxScore()+request.getScore());

            englishTestRepository.save(englishTest);

            AnswerRequest answer = new AnswerRequest();
            answer.setText("Question was added successfully");

            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Transactional
    public void deleteQuestion(UUID id) {
        try {
            EnglishTestQuestion englishTestQuestion = englishTestQuestionRepository.findById(id).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_NOT_FOUND));

            EnglishTest englishTest = englishTestRepository.findById(englishTestQuestionRepository.findById(id).get().getTestId()).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));

            englishTest.setQuestionQuantity(englishTest.getQuestionQuantity()-1);
            englishTest.setMaxScore(englishTest.getMaxScore()-englishTestQuestion.getScore());
            answerOptionRepository.deleteAllByQuestionId(id);
            englishTestQuestionRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public EnglishTestQuestion update(UUID id, EnglishTestQuestionUpdateRequest request) {
       EnglishTestQuestion englishTestQuestion = englishTestQuestionRepository.findById(id)
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_NOT_FOUND));

        englishTestQuestionMapper.updateTestQuestionFromDto(request, englishTestQuestion);

        return englishTestQuestionRepository.save(englishTestQuestion);
    }

    public List<EnglishTestQuestion> getTestQuestion(UUID testId) {
        try {
            EnglishTest englishTest = englishTestRepository.findById(testId)
                    .orElseThrow(() ->
                            PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));

            List<EnglishTestQuestion> englishTestQuestions = englishTestQuestionRepository.testQuestion(testId);

            return englishTestQuestions;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
