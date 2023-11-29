package com.example.sbbmission.answer.repository;

import com.example.sbbmission.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
