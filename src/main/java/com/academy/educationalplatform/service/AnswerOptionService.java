package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerOptionRequest;
import com.academy.educationalplatform.dto.AnswerOptionUpdateRequest;
import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.EnglishTestQuestionUpdateRequest;
import com.academy.educationalplatform.entity.AnswerOption;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.AnswerOptionMapper;
import com.academy.educationalplatform.repository.AnswerOptionRepository;
import com.academy.educationalplatform.repository.EnglishTestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerOptionMapper answerOptionMapper;
    private final EnglishTestQuestionRepository englishTestQuestionRepository;

    public AnswerOptionService(AnswerOptionRepository answerOptionRepository, AnswerOptionMapper answerOptionMapper, EnglishTestQuestionRepository englishTestQuestionRepository) {
        this.answerOptionRepository = answerOptionRepository;
        this.answerOptionMapper = answerOptionMapper;
        this.englishTestQuestionRepository = englishTestQuestionRepository;
    }

    public AnswerRequest addAnswerOption(AnswerOptionRequest request) {
        try {
            AnswerOption answerOption = answerOptionMapper.toEntity(request);
            answerOptionRepository.save(answerOption);

            AnswerRequest answer = new AnswerRequest();
            answer.setText("Question's option was added successfully");

            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<AnswerOption> getQuestionAnswer(UUID questionId) {
        try {
            englishTestQuestionRepository.findById(questionId).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.ENGLISH_QUESTION_NOT_FOUND);
            });

            List<AnswerOption> questionAnswerList = answerOptionRepository.questionAnswer(questionId);

            return questionAnswerList;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void delAnswerOption(UUID id) {
        try {
            if(!answerOptionRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.ANSWER_OPTION_NOT_FOUND);
            }

            answerOptionRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public AnswerOption update(UUID id, AnswerOptionUpdateRequest request) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.ANSWER_OPTION_NOT_FOUND));

        answerOptionMapper.updateAnswerOptionFromDto(request, answerOption);

        return answerOptionRepository.save(answerOption);
    }
}
