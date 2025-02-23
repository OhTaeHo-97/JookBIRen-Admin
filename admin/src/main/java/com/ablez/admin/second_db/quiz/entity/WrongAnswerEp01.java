package com.ablez.admin.second_db.quiz.entity;

import com.ablez.admin.second_db.user.entity.UserEp01;
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
@Table(name = "wrong_answer_ep01", indexes = {
        @Index(name = "FKd5ain6xijfoyfnjrhkwcgdyjn", columnList = "user_id"),
        @Index(name = "FKe3kcsno0k5u9gsrgall5j1bml", columnList = "quiz_id")
})
public class WrongAnswerEp01 {
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
    private UserEp01 user;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp01 quiz;

    public WrongAnswerEp01(String answer, LocalDateTime time, UserEp01 user, QuizEp01 quiz) {
        this.answer = answer;
        this.time = time;
        this.user = user;
        this.quiz = quiz;
    }
}
