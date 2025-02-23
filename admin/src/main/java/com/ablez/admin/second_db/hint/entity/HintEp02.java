package com.ablez.admin.second_db.hint.entity;

import com.ablez.admin.second_db.quiz.entity.QuizEp02;
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
@Table(name = "answer_ep02", indexes = {
        @Index(name = "FK8h1gyxi5spbo9l13fhmg849vm", columnList = "quiz_id")
})
public class HintEp02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hint_id")
    private Long hintId;
    @Column(name = "hint")
    private String hint;
    @Column(name = "hint_image")
    private String hintImage;
    @Column(name = "hint_order")
    private Integer hintOrder;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp02 quiz;
}
