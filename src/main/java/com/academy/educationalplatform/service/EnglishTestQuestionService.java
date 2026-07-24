package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.EnglishTestQuestionRequest;
import com.academy.educationalplatform.dto.RegisterEnglishQuestionRequest;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.EnglishTestQuestionMapper;
import com.academy.educationalplatform.repository.EnglishTestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnglishTestQuestionService {
    private final EnglishTestQuestionRepository englishTestQuestionRepository;
    private final EnglishTestQuestionMapper englishTestQuestionMapper;

    public EnglishTestQuestionService(EnglishTestQuestionRepository englishTestQuestionRepository, EnglishTestQuestionMapper englishTestQuestionMapper) {
        this.englishTestQuestionRepository = englishTestQuestionRepository;
        this.englishTestQuestionMapper = englishTestQuestionMapper;
    }

    public AnswerRequest createQuestion(RegisterEnglishQuestionRequest request) {
        try {
            if (englishTestQuestionRepository.existsByQuestionNumber(request.getQuestionNumber())) {
                throw PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_ALREADY_EXISTS);
            }
            EnglishTestQuestion englishTestQuestion = englishTestQuestionMapper.toEntity(request);
            englishTestQuestionRepository.save(englishTestQuestion);

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

            englishTestQuestionRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
