package com.example.sbbmission.answer.entity;


import static jakarta.persistence.FetchType.*;

import com.example.sbbmission.common.entity.BaseEntity;
import com.example.sbbmission.question.entity.Question;
import com.example.sbbmission.user.entity.SiteUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@Entity
public class Answer extends BaseEntity {


    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = LAZY)
    private Question question;

    @ManyToOne(fetch = LAZY)
    private SiteUser author;

    @ManyToMany
    @Builder.Default
    @ToString.Exclude
    Set<SiteUser> voter = new HashSet<>();

    public void modifyContent(String content) {
        this.content = content;
        this.updateModifyDate();
    }
}
