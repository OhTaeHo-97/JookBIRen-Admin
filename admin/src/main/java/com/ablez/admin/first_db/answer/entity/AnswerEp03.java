package com.ablez.admin.first_db.answer.entity;

import com.ablez.admin.first_db.quiz.entity.episode3.QuizEp03;
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
@Table(name = "answer_ep03", indexes = {
        @Index(name = "idx_quiz_id", columnList = "quiz_id")
})
public class AnswerEp03 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;
    @Column(name = "answer", nullable = false)
    private String answer;
    @OneToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp03 quizEp03;
}
