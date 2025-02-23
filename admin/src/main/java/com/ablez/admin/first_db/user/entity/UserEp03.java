package com.ablez.admin.first_db.user.entity;

import com.ablez.admin.first_db.quiz.entity.episode3.Quiz0Ep03;
import com.ablez.admin.first_db.quiz.entity.episode3.Quiz1Ep03;
import com.ablez.admin.first_db.quiz.entity.episode3.Quiz2Ep03;
import com.ablez.admin.first_db.quiz.entity.episode3.Quiz3Ep03;
import com.ablez.admin.first_db.quiz.entity.episode3.WrongAnswerEp03;
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

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_ep03", indexes = {
        @Index(name = "idx_user_info_id", columnList = "user_info_id")
})
public class UserEp03 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Setter
    @Column(name = "answer_count")
    private Integer answerCount = 0;
    @Setter
    @Column(name = "answer_status_code")
    private Integer answerStatusCode = 0;
    @Column(name = "criminal")
    private Integer criminal = 0;
    @Setter
    @Column(name = "score")
    private Integer score = 0;
    @Setter
    @Column(name = "solved_quiz_count")
    private Integer solvedQuizCount = 0;
    @Setter
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "code", nullable = false)
    private String code;
    @Setter
    @Column(name = "answer_time")
    private LocalDateTime answerTime;
    @Column(name = "first_login_time")
    private LocalDateTime firstLoginTime;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    @Setter
    private UserInfo userInfo;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<Quiz0Ep03> quiz0s = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz1Ep03> quiz1s = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz2Ep03> quiz2s = new ArrayList<>();

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private Quiz3Ep03 quiz3s;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WrongAnswerEp03> wrongAnswers = new ArrayList<>();

    public UserEp03(String code) {
        this.code = code;
    }

    public UserEp03(String code, UserInfo userInfo) {
        this.code = code;
        this.userInfo = userInfo;
        userInfo.setUserEp03(this);
    }
}
