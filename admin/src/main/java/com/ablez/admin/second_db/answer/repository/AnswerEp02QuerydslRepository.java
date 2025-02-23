package com.ablez.admin.second_db.answer.repository;

import static com.ablez.admin.second_db.answer.entity.QAnswerEp02.answerEp02;
import static com.ablez.admin.second_db.quiz.entity.QQuizEp02.quizEp02;

import com.ablez.admin.second_db.answer.entity.AnswerEp02;
import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerEp02QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public AnswerEp02QuerydslRepository() {
        super(AnswerEp02.class);
    }

    public List<AnswerEp02> findAll() {
        return selectFrom(answerEp02)
                .innerJoin(answerEp02.quizEp02, quizEp02).fetchJoin()
                .distinct()
                .fetch();
    }
}
