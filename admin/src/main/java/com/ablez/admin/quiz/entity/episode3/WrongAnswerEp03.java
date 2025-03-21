package com.ablez.admin.quiz.entity.episode3;

import com.ablez.admin.user.entity.UserEp03;
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

@NoArgsConstructor
@Getter
@Entity
@Table(name = "wrong_answer_ep03", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_quiz_id", columnList = "quiz_id")
})
public class WrongAnswerEp03 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wrong_answer_id")
    private Long wrongAnswerId;
    @Column(name = "answer")
    private String answer;
    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEp03 user;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp03 quiz;

    public WrongAnswerEp03(String answer, LocalDateTime time, UserEp03 user, QuizEp03 quiz) {
        this.answer = answer;
        this.time = time;
        this.user = user;
        this.quiz = quiz;
    }
}
