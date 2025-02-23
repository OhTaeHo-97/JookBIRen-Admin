package com.ablez.admin.second_db.user.entity;

import com.ablez.admin.second_db.quiz.entity.Quiz0Ep01;
import com.ablez.admin.second_db.quiz.entity.Quiz1Ep01;
import com.ablez.admin.second_db.quiz.entity.Quiz2Ep01;
import com.ablez.admin.second_db.quiz.entity.Quiz3Ep01;
import com.ablez.admin.second_db.quiz.entity.Quiz4Ep01;
import com.ablez.admin.second_db.quiz.entity.WrongAnswerEp01;
import com.ablez.admin.second_db.security.entity.AuthorityEp01;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_ep01", indexes = {
        @Index(name = "FKlry4tsevxwfckuodkb2mxn85q", columnList = "user_info_id")
})
public class UserEp01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "code", nullable = false)
    private String code;
    @Setter
    @ColumnDefault("0")
    @Column(name = "score")
    private Integer score = 0;
    @Setter
    @ColumnDefault("0")
    @Column(name = "answer_status_code")
    private Integer answerStatusCode = 0;
    @Setter
    @ColumnDefault("0")
    @Column(name = "answer_count")
    private Integer answerCount = 0;
    @Setter
    @ColumnDefault("0")
    @Column(name = "solved_quiz_count")
    private Integer solvedQuizCount = 0;
    @Setter
    @ColumnDefault("0")
    @Column(name = "criminal")
    private Integer criminal = 0;
    @Setter
    @Column(name = "access_token")
    private String accessToken;
    @Setter
    @Column(name = "answer_time")
    private LocalDateTime answerTime;
    @Column(name = "first_login_time")
    private LocalDateTime firstLoginTime;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter
    private Quiz0Ep01 quiz0s;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    @Setter
    private UserInfoEp01 userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<AuthorityEp01> authorities = new HashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz1Ep01> quiz1s = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz2Ep01> quiz2s = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz3Ep01> quiz3s = new ArrayList<>();

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter
    private Quiz4Ep01 quiz4s;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WrongAnswerEp01> wrongAnswers = new ArrayList<>();

    public UserEp01(String code) {
        this.code = code;
    }

    public void updateCriminal(int criminal) {
        this.criminal = criminal;
    }

    public void updateFirstLoginTime() {
        if (this.firstLoginTime == null) {
            firstLoginTime = LocalDateTime.now();
        }
    }

    public List<String> getRoles() {
        return authorities.stream().map(AuthorityEp01::getRole).collect(Collectors.toList());
    }

    public void addRole(AuthorityEp01 authorityEp01) {
        this.authorities.add(authorityEp01);
    }

    public void addQuiz1(Quiz1Ep01 quiz1Ep01) {
        this.quiz1s.add(quiz1Ep01);
    }

    public void addQuiz2(Quiz2Ep01 quiz2Ep01) {
        this.quiz2s.add(quiz2Ep01);
    }

    public void addQuiz3(Quiz3Ep01 quiz3Ep01) {
        this.quiz3s.add(quiz3Ep01);
    }
}
