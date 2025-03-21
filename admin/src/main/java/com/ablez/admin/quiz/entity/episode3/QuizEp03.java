package com.ablez.admin.quiz.entity.episode3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "quiz_ep03")
public class QuizEp03 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long quizId;
    @Column(name = "place_code", nullable = false)
    private Integer placeCode;
    @Column(name = "quiz_number", nullable = false)
    private Integer quizNumber;
    @Column(name = "quiz_code", nullable = false)
    private Integer quizCode;
}
