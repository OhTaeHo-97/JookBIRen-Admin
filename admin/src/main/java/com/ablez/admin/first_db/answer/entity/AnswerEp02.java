package com.ablez.admin.first_db.answer.entity;

import com.ablez.admin.first_db.quiz.entity.episode2.QuizEp02;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "answer_ep02", indexes = {
        @Index(name = "idx_quiz_id", columnList = "quiz_id")
})
public class AnswerEp02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;
    @Column(name = "answer", nullable = false)
    private String answer;
    @OneToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp02 quizEp02;

    public AnswerEp02(String answer, QuizEp02 quizEp02) {
        this.answer = answer;
        this.quizEp02 = quizEp02;
    }
}
