package com.ablez.admin.second_db.answer.entity;

import com.ablez.admin.second_db.quiz.entity.QuizEp02;
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
        @Index(name = "FKdiwwt8dea1okba2q7fnbe1naw", columnList = "quiz_id")
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
}
