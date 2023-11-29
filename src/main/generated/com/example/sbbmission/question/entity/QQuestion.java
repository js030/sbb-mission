package com.example.sbbmission.question.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -1381155251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final com.example.sbbmission.common.entity.QBaseEntity _super = new com.example.sbbmission.common.entity.QBaseEntity(this);

    public final ListPath<com.example.sbbmission.answer.entity.Answer, com.example.sbbmission.answer.entity.QAnswer> answerList = this.<com.example.sbbmission.answer.entity.Answer, com.example.sbbmission.answer.entity.QAnswer>createList("answerList", com.example.sbbmission.answer.entity.Answer.class, com.example.sbbmission.answer.entity.QAnswer.class, PathInits.DIRECT2);

    public final com.example.sbbmission.user.entity.QSiteUser author;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final StringPath subject = createString("subject");

    public final SetPath<com.example.sbbmission.user.entity.SiteUser, com.example.sbbmission.user.entity.QSiteUser> voter = this.<com.example.sbbmission.user.entity.SiteUser, com.example.sbbmission.user.entity.QSiteUser>createSet("voter", com.example.sbbmission.user.entity.SiteUser.class, com.example.sbbmission.user.entity.QSiteUser.class, PathInits.DIRECT2);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.example.sbbmission.user.entity.QSiteUser(forProperty("author")) : null;
    }

}

