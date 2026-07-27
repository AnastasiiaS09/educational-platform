package com.academy.educationalplatform.service;


import com.academy.educationalplatform.dto.AnswerOptionRequest;
import com.academy.educationalplatform.dto.AnswerOptionUpdateRequest;
import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.RegisterEnglishQuestionRequest;
import com.academy.educationalplatform.entity.AnswerOption;
import com.academy.educationalplatform.entity.EnglishTestQuestion;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.AnswerOptionMapper;
import com.academy.educationalplatform.mapper.EnglishTestQuestionMapper;
import com.academy.educationalplatform.repository.AnswerOptionRepository;
import com.academy.educationalplatform.repository.EnglishTestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerOptionService {

    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerOptionMapper answerOptionMapper;

    public AnswerOptionService(AnswerOptionRepository answerOptionRepository, AnswerOptionMapper answerOptionMapper) {
        this.answerOptionRepository = answerOptionRepository;
        this.answerOptionMapper = answerOptionMapper;
    }

    public AnswerOption createAnswerOption(AnswerOptionRequest request) {
        try {
            AnswerOption answerOption = new AnswerOption();
            answerOption.setQuestionId(request.getQuestionId());
            answerOption.setOptionText(request.getOptionText());
            answerOption.setOptionCorrectness(request.getOptionCorrectness());

            AnswerRequest answer = new AnswerRequest();
            answer.setText("Question was added successfully");

            return answerOption;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteAnswerOption(UUID id) {
        try {
            AnswerOption  answerOption = answerOptionRepository.findById(id)
                    .orElseThrow(() ->
                            PlatformException.of(PlatformErrorCode.ANSWER_OPTION_NOT_FOUND));

            answerOptionRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public AnswerOption updateAnswerOption(UUID id, AnswerOptionUpdateRequest request) {
        AnswerOption  answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.ANSWER_OPTION_NOT_FOUND));

        answerOptionMapper.updateAnswerOptionFromDto(request, answerOption);

        return answerOptionRepository.save(answerOption);
    }


}
