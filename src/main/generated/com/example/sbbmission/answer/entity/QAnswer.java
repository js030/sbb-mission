package com.example.sbbmission.answer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = 1412936381L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswer answer = new QAnswer("answer");

    public final com.example.sbbmission.common.entity.QBaseEntity _super = new com.example.sbbmission.common.entity.QBaseEntity(this);

    public final com.example.sbbmission.user.entity.QSiteUser author;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final com.example.sbbmission.question.entity.QQuestion question;

    public final SetPath<com.example.sbbmission.user.entity.SiteUser, com.example.sbbmission.user.entity.QSiteUser> voter = this.<com.example.sbbmission.user.entity.SiteUser, com.example.sbbmission.user.entity.QSiteUser>createSet("voter", com.example.sbbmission.user.entity.SiteUser.class, com.example.sbbmission.user.entity.QSiteUser.class, PathInits.DIRECT2);

    public QAnswer(String variable) {
        this(Answer.class, forVariable(variable), INITS);
    }

    public QAnswer(Path<? extends Answer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswer(PathMetadata metadata, PathInits inits) {
        this(Answer.class, metadata, inits);
    }

    public QAnswer(Class<? extends Answer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.example.sbbmission.user.entity.QSiteUser(forProperty("author")) : null;
        this.question = inits.isInitialized("question") ? new com.example.sbbmission.question.entity.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

