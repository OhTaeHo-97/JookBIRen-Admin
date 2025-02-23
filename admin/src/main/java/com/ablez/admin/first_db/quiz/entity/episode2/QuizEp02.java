package com.ablez.admin.first_db.quiz.entity.episode2;

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
@Table(name = "quiz_ep02")
public class QuizEp02 {
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

    public QuizEp02(Integer placeCode, Integer quizNumber, Integer quizCode) {
        this.placeCode = placeCode;
        this.quizNumber = quizNumber;
        this.quizCode = quizCode;
    }
}
