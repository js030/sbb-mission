package com.example.sbbmission.question.repository;

import com.example.sbbmission.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryCustom {

    Page<Question> findAllByKeyword(String kw, Pageable pageable);
}
