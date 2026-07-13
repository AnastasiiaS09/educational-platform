package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.EnglishTestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnglishTestQuestionService {
    private final EnglishTestQuestionRepository englishTestQuestionRepository;

    public EnglishTestQuestionService(EnglishTestQuestionRepository englishTestQuestionRepository) {
        this.englishTestQuestionRepository = englishTestQuestionRepository;
    }

    public EnglishTestQuestion createQuestion(UUID testId, int questionNumber, String correctAnswer) {
        try {
            if (!englishTestQuestionRepository.existsByQuestionNumber(questionNumber)) {
                throw PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_ALREADY_EXISTS);
            }
            EnglishTestQuestion englishTestQuestion = new EnglishTestQuestion();
            englishTestQuestion.setTestId(testId);
            englishTestQuestion.setQuestionNumber(questionNumber);
            englishTestQuestion.setCorrectAnswer(correctAnswer);

            return englishTestQuestionRepository.save(englishTestQuestion);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
