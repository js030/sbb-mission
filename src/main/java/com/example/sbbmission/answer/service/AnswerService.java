package com.example.sbbmission.answer.service;

import com.example.sbbmission.common.exception.DataNotFoundException;
import com.example.sbbmission.answer.entity.Answer;
import com.example.sbbmission.answer.repository.AnswerRepository;
import com.example.sbbmission.question.entity.Question;
import com.example.sbbmission.user.entity.SiteUser;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Transactional
    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = Answer.builder()
                .content(content)
                .question(question)
                .author(author)
                .build();

        this.answerRepository.save(answer);
        return answer;
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    @Transactional
    public void modify(Answer answer, String content) {
        answer.modifyContent(content);
        this.answerRepository.save(answer);
    }

    @Transactional
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    @Transactional
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
