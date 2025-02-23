package com.ablez.admin.first_db.user.entity;

import com.ablez.admin.first_db.quiz.entity.episode1.Quiz0Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz1Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz2Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz3Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz4Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.WrongAnswerEp01;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
        @Index(name = "idx_user_info_id", columnList = "user_info_id")
})
public class UserEp01 {
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

    @OneToOne
    @JoinColumn(name = "user_info_id")
    @Setter
    private UserInfo userInfo;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private Quiz0Ep01 quiz0s;

    @Setter
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz1Ep01> quiz1s = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz2Ep01> quiz2s = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz3Ep01> quiz3s = new ArrayList<>();

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private Quiz4Ep01 quiz4s;

    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WrongAnswerEp01> wrongAnswers = new ArrayList<>();

    public UserEp01(String code) {
        this.code = code;
    }

    public UserEp01(String code, UserInfo userInfo) {
        this.code = code;
        this.userInfo = userInfo;
        userInfo.setUserEp01(this);
    }

    public UserEp01(String code, Integer score, Integer answerStatusCode, Integer answerCount, Integer solvedQuizCount,
                    Integer criminal, String accessToken, LocalDateTime answerTime, LocalDateTime firstLoginTime,
                    UserInfo userInfo) {
        this.code = code;
        this.score = score;
        this.answerStatusCode = answerStatusCode;
        this.answerCount = answerCount;
        this.solvedQuizCount = solvedQuizCount;
        this.criminal = criminal;
        this.accessToken = accessToken;
        this.answerTime = answerTime;
        this.firstLoginTime = firstLoginTime;
        this.userInfo = userInfo;
        userInfo.setUserEp01(this);
    }
}
