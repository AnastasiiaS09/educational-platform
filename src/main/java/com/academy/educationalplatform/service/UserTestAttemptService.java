package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.UserTestRequest;
import com.academy.educationalplatform.entity.UserTestAttempt;
import com.academy.educationalplatform.repository.UserTestAttemptRepository;
import com.academy.educationalplatform.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserTestAttemptService {
    private final UserTestAttemptRepository userTestAttemptRepository;

    public UserTestAttemptService(UserTestAttemptRepository userTestAttemptRepository) {
        this.userTestAttemptRepository = userTestAttemptRepository;
    }

    public AnswerRequest addUserTest(UserTestRequest request) {

        UUID id = SecurityUtils.currentUserId();
        UserTestAttempt userTestAttempt = new UserTestAttempt();
        userTestAttempt.setUserId(id);
        userTestAttempt.setTestId(request.getTestId());

        userTestAttemptRepository.save(userTestAttempt);

        AnswerRequest answer = new AnswerRequest();
        answer.setText("You signed up for the test successfully");

        return answer;
    }

    public void delUserTest(UUID id) {
        userTestAttemptRepository.deleteById(id);
    }

}
