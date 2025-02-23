package com.ablez.admin.second_db.quiz.entity;

import com.ablez.admin.second_db.user.entity.UserEp02;
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
@Table(name = "wrong_answer_ep02", indexes = {
        @Index(name = "FKhhf9tc1w1fmb031bdesjyfdfn", columnList = "user_id"),
        @Index(name = "FKd9jw2dyovhu3q6veo8n56up27", columnList = "quiz_id")
})
public class WrongAnswerEp02 {
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
    private UserEp02 user;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp02 quiz;

    public WrongAnswerEp02(String answer, LocalDateTime time, UserEp02 user, QuizEp02 quiz) {
        this.answer = answer;
        this.time = time;
        this.user = user;
        this.quiz = quiz;
    }
}
