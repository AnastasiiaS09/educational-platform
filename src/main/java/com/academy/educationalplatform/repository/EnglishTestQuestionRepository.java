package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.EnglishTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnglishTestQuestionRepository extends JpaRepository<EnglishTestQuestion, UUID> {

    boolean existsByQuestionNumber(int questionNumber);
}
