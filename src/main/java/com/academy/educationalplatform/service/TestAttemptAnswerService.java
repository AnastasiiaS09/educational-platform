package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.TestAttemptAnswerRequest;
import com.academy.educationalplatform.dto.TestAttemptAnswerResponse;
import com.academy.educationalplatform.entity.*;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.AnswerOptionRepository;
import com.academy.educationalplatform.repository.EnglishTestQuestionRepository;
import com.academy.educationalplatform.repository.TestAttemptAnswerRepository;
import com.academy.educationalplatform.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestAttemptAnswerService {
    private final TestAttemptAnswerRepository testAttemptAnswerRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final EnglishTestQuestionRepository englishTestQuestionRepository;

    public TestAttemptAnswerService(TestAttemptAnswerRepository testAttemptAnswerRepository, AnswerOptionRepository answerOptionRepository, EnglishTestQuestionRepository englishTestQuestionRepository) {
        this.testAttemptAnswerRepository = testAttemptAnswerRepository;
        this.answerOptionRepository = answerOptionRepository;
        this.englishTestQuestionRepository = englishTestQuestionRepository;
    }

    public TestAttemptAnswer addTestAttemptAnswer(TestAttemptAnswerRequest request) {
        try {

            TestAttemptAnswer testAttemptAnswer = new TestAttemptAnswer();
            EnglishTestQuestion question = englishTestQuestionRepository.findById(request.getQuestionId())
                    .orElseThrow(() -> PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_NOT_FOUND));
            AnswerOption correctOption = answerOptionRepository.correctAnswer(question.getId());

            testAttemptAnswer.setAttemptId(request.getAttemptId());
            testAttemptAnswer.setQuestionId(request.getQuestionId());
            testAttemptAnswer.setUserAnswerId(request.getUserAnswerId());
            if(!request.getUserAnswerId().equals(correctOption.getId())) {
                testAttemptAnswer.setCorrectness(EnglishTestAnswer.WRONG);
            } else {
                testAttemptAnswer.setCorrectness(EnglishTestAnswer.CORRECT);
            }


            return testAttemptAnswerRepository.save(testAttemptAnswer);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
