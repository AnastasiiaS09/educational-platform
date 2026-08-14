package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.EnglishTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EnglishTestQuestionRepository extends JpaRepository<EnglishTestQuestion, UUID> {

    boolean existsByQuestionNumber(Integer questionNumber);

    @Query("""
    FROM EnglishTestQuestion tq WHERE tq.testId = :testId
    ORDER BY tq.questionNumber
""")
    List<EnglishTestQuestion> testQuestion(UUID testId);
}
