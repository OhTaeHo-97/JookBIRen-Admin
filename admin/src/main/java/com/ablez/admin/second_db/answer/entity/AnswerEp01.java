package com.ablez.admin.second_db.answer.entity;

import com.ablez.admin.second_db.quiz.entity.QuizEp01;
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
@Table(name = "answer_ep01", indexes = {
        @Index(name = "FKmslvhw8y90xomtkg3r9sp5if7", columnList = "quiz_id")
})
public class AnswerEp01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;
    @Column(name = "answer", nullable = false)
    private String answer;
    @OneToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp01 quizEp01;
}
