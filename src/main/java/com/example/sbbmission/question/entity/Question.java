package com.example.sbbmission.question.entity;

import static jakarta.persistence.FetchType.*;

import com.example.sbbmission.answer.entity.Answer;
import com.example.sbbmission.common.entity.BaseEntity;
import com.example.sbbmission.user.entity.SiteUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class Question extends BaseEntity {

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Answer> answerList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    private SiteUser author;

    @ManyToMany
    @Builder.Default
    @ToString.Exclude
    Set<SiteUser> voter = new HashSet<>();

    public void modify(String subject, String content) {
        this.subject = subject;
        this.content = content;
        this.updateModifyDate();
    }
}
