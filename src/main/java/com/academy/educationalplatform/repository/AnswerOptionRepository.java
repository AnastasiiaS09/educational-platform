package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, UUID> {
    void deleteAllByQuestionId(UUID questionId);

    @Query("""
    FROM AnswerOption ao WHERE ao.questionId = :questionId
""")
    List<AnswerOption> questionAnswer(UUID questionId);
}
