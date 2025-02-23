package com.ablez.admin.first_db.quiz.entity.episode1;

import com.ablez.admin.first_db.user.entity.UserEp01;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "quiz4ep01", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_quiz_id", columnList = "quiz_id")
})
public class Quiz4Ep01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz4id")
    private Long quiz4Id;
    @Column(name = "quiz_number")
    private Integer quizNumber;
    @Setter
    @Column(name = "first_access_time")
    private LocalDateTime firstAccessTime;
    @Setter
    @Column(name = "first_answer_time")
    private LocalDateTime firstAnswerTime;
    @Setter
    @Column(name = "get_hint_time")
    private LocalDateTime getHintTime;
    @Setter
    @Column(name = "get_answer_time")
    private LocalDateTime getAnswerTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEp01 userId;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp01 quiz;

    public Quiz4Ep01(Integer quizNumber, UserEp01 userId, QuizEp01 quiz) {
        this.quizNumber = quizNumber;
        this.firstAccessTime = LocalDateTime.now();
        this.userId = userId;
        this.quiz = quiz;
    }

    public Quiz4Ep01(Integer quizNumber, LocalDateTime firstAccessTime, LocalDateTime firstAnswerTime,
                     LocalDateTime getHintTime, LocalDateTime getAnswerTime, UserEp01 userId, QuizEp01 quiz) {
        this.quizNumber = quizNumber;
        this.firstAccessTime = firstAccessTime;
        this.firstAnswerTime = firstAnswerTime;
        this.getHintTime = getHintTime;
        this.getAnswerTime = getAnswerTime;
        this.userId = userId;
        this.quiz = quiz;
    }
}
