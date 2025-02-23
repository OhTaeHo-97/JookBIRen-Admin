package com.ablez.admin.second_db.answer.repository;

import static com.ablez.admin.second_db.answer.entity.QAnswerEp01.answerEp01;
import static com.ablez.admin.second_db.quiz.entity.QQuizEp01.quizEp01;

import com.ablez.admin.second_db.answer.entity.AnswerEp01;
import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerEp01QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public AnswerEp01QuerydslRepository() {
        super(AnswerEp01.class);
    }

    public List<AnswerEp01> findAll() {
        return selectFrom(answerEp01)
                .innerJoin(answerEp01.quizEp01, quizEp01).fetchJoin()
                .distinct()
                .fetch();
    }
}
