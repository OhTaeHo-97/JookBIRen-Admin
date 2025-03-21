package com.ablez.admin.quiz.entity.episode2;

import com.ablez.admin.user.entity.UserEp02;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "quiz2ep02", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_quiz_id", columnList = "quiz_id")
})
public class Quiz2Ep02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz2id")
    private Long quiz2Id;
    @Column(name = "quiz_number")
    private Integer quizNumber;
    @Setter
    @Column(name = "first_access_time")
    private LocalDateTime firstAccessTime;
    @Setter
    @Column(name = "first_answer_time")
    private LocalDateTime firstAnswerTime;
    @Setter
    @Column(name = "first_hint_time")
    private LocalDateTime firstHintTime;
    @Setter
    @Column(name = "second_hint_time")
    private LocalDateTime secondHintTime;
    @Setter
    @Column(name = "get_answer_time")
    private LocalDateTime getAnswerTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEp02 userId;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp02 quiz;

    public Quiz2Ep02(Integer quizNumber, UserEp02 userId, QuizEp02 quiz) {
        this.quizNumber = quizNumber;
        this.firstAccessTime = LocalDateTime.now();
        this.userId = userId;
        this.quiz = quiz;
    }

    public Quiz2Ep02(Integer quizNumber, LocalDateTime firstAccessTime, LocalDateTime firstAnswerTime,
                     LocalDateTime firstHintTime, LocalDateTime secondHintTime, LocalDateTime getAnswerTime,
                     UserEp02 userId, QuizEp02 quiz) {
        this.quizNumber = quizNumber;
        this.firstAccessTime = firstAccessTime;
        this.firstAnswerTime = firstAnswerTime;
        this.firstHintTime = firstHintTime;
        this.secondHintTime = secondHintTime;
        this.getAnswerTime = getAnswerTime;
        this.userId = userId;
        this.quiz = quiz;
    }
}
