package com.ablez.admin.second_db.user.entity;

import com.ablez.admin.second_db.quiz.entity.Quiz0Ep02;
import com.ablez.admin.second_db.quiz.entity.Quiz1Ep02;
import com.ablez.admin.second_db.quiz.entity.Quiz2Ep02;
import com.ablez.admin.second_db.quiz.entity.Quiz3Ep02;
import com.ablez.admin.second_db.quiz.entity.WrongAnswerEp02;
import com.ablez.admin.second_db.security.entity.AuthorityEp02;
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
@Table(name = "user_ep02", indexes = {
        @Index(name = "FKaga2rv9te94e8jhea44pufrpp", columnList = "user_info_id")
})
public class UserEp02 {
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
    @Column(name = "criminal1")
    private Integer criminal1 = 0;
    @Setter
    @ColumnDefault("0")
    @Column(name = "criminal2")
    private Integer criminal2 = 0;
    @Setter
    @Column(name = "access_token")
    private String accessToken;
    @Setter
    @Column(name = "answer_time")
    private LocalDateTime answerTime;
    @Column(name = "first_login_time")
    private LocalDateTime firstLoginTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    @Setter
    private UserInfoEp02 userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<AuthorityEp02> authorities = new HashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz0Ep02> quiz0s = new ArrayList<>();
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz1Ep02> quiz1s = new ArrayList<>();
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz2Ep02> quiz2s = new ArrayList<>();
    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Quiz3Ep02 quiz3;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WrongAnswerEp02> wrongAnswers = new ArrayList<>();

    public UserEp02(String code) {
        this.code = code;
    }

    public List<String> getRoles() {
        return authorities.stream().map(AuthorityEp02::getRole).collect(Collectors.toList());
    }

    public void updateCriminal1(int criminal1) {
        this.criminal1 = criminal1;
    }

    public void updateCriminal2(int criminal2) {
        this.criminal2 = criminal2;
    }

    public void addQuiz0(Quiz0Ep02 quiz0) {
        this.quiz0s.add(quiz0);
    }

    public void addQuiz1(Quiz1Ep02 quiz1) {
        this.quiz1s.add(quiz1);
    }

    public void addQuiz2(Quiz2Ep02 quiz2) {
        this.quiz2s.add(quiz2);
    }

    public void setQuiz3(Quiz3Ep02 quiz3) {
        this.quiz3 = quiz3;
    }

    public void updateFirstLoginTime() {
        if (this.firstLoginTime == null) {
            firstLoginTime = LocalDateTime.now();
        }
    }

    public void addRole(AuthorityEp02 authority) {
        this.authorities.add(authority);
    }
}
