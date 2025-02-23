package com.ablez.admin.first_db.user.entity;

import com.ablez.admin.first_db.quiz.entity.episode2.Quiz0Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz1Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz2Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz3Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.WrongAnswerEp02;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        @Index(name = "idx_user_info_id", columnList = "user_info_id")
})
public class UserEp02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "code", nullable = false, unique = true)
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

    @OneToOne
    @JoinColumn(name = "user_info_id")
    @Setter
    private UserInfo userInfo;

    @Setter
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz0Ep02> quiz0s = new ArrayList<>();
    @Setter
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz1Ep02> quiz1s = new ArrayList<>();
    @Setter
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz2Ep02> quiz2s = new ArrayList<>();
    @Setter
    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Quiz3Ep02 quiz3;
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WrongAnswerEp02> wrongAnswers = new ArrayList<>();

    public UserEp02(String code) {
        this.code = code;
    }

    public UserEp02(String code, UserInfo userInfo) {
        this.code = code;
        this.userInfo = userInfo;
        userInfo.setUserEp02(this);
    }

    public UserEp02(String code, Integer score, Integer answerStatusCode, Integer answerCount, Integer solvedQuizCount,
                    Integer criminal1, Integer criminal2, String accessToken, LocalDateTime answerTime,
                    LocalDateTime firstLoginTime, UserInfo userInfo) {
        this.code = code;
        this.score = score;
        this.answerStatusCode = answerStatusCode;
        this.answerCount = answerCount;
        this.solvedQuizCount = solvedQuizCount;
        this.criminal1 = criminal1;
        this.criminal2 = criminal2;
        this.accessToken = accessToken;
        this.answerTime = answerTime;
        this.firstLoginTime = firstLoginTime;
        this.userInfo = userInfo;
        userInfo.setUserEp02(this);
    }
}
