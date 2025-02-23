package com.ablez.admin.second_db.quiz.repository;

import static com.ablez.admin.second_db.quiz.entity.QQuizEp01.quizEp01;

import com.ablez.admin.second_db.quiz.entity.QuizEp01;
import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class QuizEp01QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public QuizEp01QuerydslRepository() {
        super(QuizEp01.class);
    }

    public List<QuizEp01> findAll() {
        return selectFrom(quizEp01)
                .fetch();
    }
}
