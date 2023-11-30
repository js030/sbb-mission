package com.example.sbbmission.question.repository;

import com.example.sbbmission.answer.entity.QAnswer;
import com.example.sbbmission.question.entity.QQuestion;
import com.example.sbbmission.question.entity.Question;
import com.example.sbbmission.user.entity.QSiteUser;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    public QuestionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Question> findAllByKeyword(String kw, Pageable pageable) {

        QQuestion question = QQuestion.question;
        QAnswer answer = QAnswer.answer;
        QSiteUser user = QSiteUser.siteUser;

        JPQLQuery<Question> query = queryFactory
                .selectFrom(question)
                .leftJoin(question.author, user)
                .leftJoin(question.answerList, answer)
                .leftJoin(answer.author, user)
                .where(question.subject.contains(kw)
                        .or(question.content.contains(kw))
                        .or(user.username.contains(kw))
                        .or(answer.content.contains(kw))
                        .or(user.username.contains(kw))
                );

        JPQLQuery<Question> paginatedQuery = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        if (pageable.getSort().isSorted()) {
            pageable.getSort().stream().forEach(order -> {
                OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(
                        order.isAscending() ? Order.ASC : Order.DESC,
                        Expressions.stringPath(order.getProperty())
                );
                paginatedQuery.orderBy(orderSpecifier);
            });
        }

        List<Question> content = paginatedQuery.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

}
