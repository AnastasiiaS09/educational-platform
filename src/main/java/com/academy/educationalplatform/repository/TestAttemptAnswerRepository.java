package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.TestAttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TestAttemptAnswerRepository extends JpaRepository<TestAttemptAnswer, UUID> {
    boolean existsByAttemptId(UUID attemptId);

    TestAttemptAnswer findByUserAnswerIdAndAttemptId(UUID userAnswerId, UUID attemptId);
}
