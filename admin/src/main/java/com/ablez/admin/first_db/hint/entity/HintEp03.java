package com.ablez.admin.first_db.hint.entity;

import com.ablez.admin.first_db.quiz.entity.episode3.QuizEp03;
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
@Table(name = "hint_ep03", indexes = {
        @Index(name = "idx_quiz_id", columnList = "quiz_id")
})
public class HintEp03 {
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
    private QuizEp03 quiz;
}
