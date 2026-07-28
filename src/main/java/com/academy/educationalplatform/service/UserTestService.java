package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.UserTestRequest;
import com.academy.educationalplatform.entity.UserTest;
import com.academy.educationalplatform.repository.UserTestRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserTestService {
    private final UserTestRepository userTestRepository;

    public UserTestService(UserTestRepository userTestRepository) {
        this.userTestRepository = userTestRepository;
    }

    public AnswerRequest addUserTest(UserTestRequest request) {
        UserTest userTest = new UserTest();
        userTest.setUserId(request.getUserId());
        userTest.setTestId(request.getTestId());

        userTestRepository.save(userTest);

        AnswerRequest answer = new AnswerRequest();
        answer.setText("You signed up for the test successfully");

        return answer;
    }

    public void delUserTest(UUID id) {
        userTestRepository.deleteById(id);
    }

}
