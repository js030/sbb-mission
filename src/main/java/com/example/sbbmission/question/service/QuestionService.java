package com.example.sbbmission.question.service;

import com.example.sbbmission.answer.entity.Answer;
import com.example.sbbmission.common.dto.RsData;
import com.example.sbbmission.common.exception.DataNotFoundException;
import com.example.sbbmission.question.entity.Question;
import com.example.sbbmission.question.repository.QuestionRepository;
import com.example.sbbmission.user.entity.SiteUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;


    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        PageRequest pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(kw); // Specification 활용
        questionRepository.findAllByKeyword(kw, pageable); // 그냥 @Query 활용하여 sql 쿼리 작성
        return questionRepository.findAllByKeyword(kw, pageable); // QueryDsl 활용
    }

    public RsData<Question> getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {

            return RsData.of("200", "%s님의 질문이 성공적으로 등록되었습니다.".formatted(question.get().getAuthor()),question.get());
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    @Transactional
    public void create(String subject, String content, SiteUser user) {
        Question q = Question.builder()
                .subject(subject)
                .content(content)
                .author(user)
                .build();

        this.questionRepository.save(q);
    }

    @Transactional
    public void modify(Question question, String subject, String content) {
        question.modify(subject, content);
        this.questionRepository.save(question);
    }

    @Transactional
    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    @Transactional
    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"),
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자

            }
        };
    }
}
