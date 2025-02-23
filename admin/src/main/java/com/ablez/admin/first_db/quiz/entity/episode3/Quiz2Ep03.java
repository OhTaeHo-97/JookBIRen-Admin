package com.ablez.admin.first_db.quiz.entity.episode3;

import com.ablez.admin.first_db.user.entity.UserEp03;
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
@Table(name = "quiz2ep03", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_quiz_id", columnList = "quiz_id")
})
public class Quiz2Ep03 {
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
    @Column(name = "first_hint_time")
    private LocalDateTime firstHintTime;
    @Setter
    @Column(name = "second_hint_time")
    private LocalDateTime secondHintTime;
    @Setter
    @Column(name = "get_answer_time")
    private LocalDateTime getAnswerTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEp03 userId;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEp03 quiz;

    public Quiz2Ep03(Integer quizNumber, UserEp03 userId, QuizEp03 quiz) {
        this.quizNumber = quizNumber;
        this.firstAccessTime = LocalDateTime.now();
        this.userId = userId;
        this.quiz = quiz;
    }
}
