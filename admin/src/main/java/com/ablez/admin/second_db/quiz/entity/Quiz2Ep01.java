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
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "quiz2ep01", indexes = {
        @Index(name = "FKldxxqm68ic69ulmv6ixuy7olo", columnList = "user_id"),
        @Index(name = "FK398l2ebe2i4rc93sipeh2e32", columnList = "quiz_id")
})
public class Quiz2Ep01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz2id")
    private Long quiz2Id;
    @Column(name = "quiz_number")
    private Integer quizNumber;
    @Setter
    @Column(name = "first_access_time")
    private LocalDateTime firstAccessTime;
    @Setter
    @Column(name = "first_answer_time")
    private LocalDateTime firstAnswerTime;
    @Setter
    @Column(name = "get_hint_time")
    private LocalDateTime getHintTime;
    @Setter
    @Column(name = "get_answer_time")
    private LocalDateTime getAnswerTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEp01 userId;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp01 quiz;

    public Quiz2Ep01(Integer quizNumber, UserEp01 userId, QuizEp01 quiz) {
        this.quizNumber = quizNumber;
        this.firstAccessTime = LocalDateTime.now();
        this.userId = userId;
        this.quiz = quiz;
    }
}
