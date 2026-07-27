package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, UUID> {
}
