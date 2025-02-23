package com.ablez.admin.second_db.quiz.repository;

import static com.ablez.admin.second_db.quiz.entity.QQuizEp02.quizEp02;

import com.ablez.admin.second_db.quiz.entity.QuizEp02;
import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class QuizEp02QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public QuizEp02QuerydslRepository() {
        super(QuizEp02.class);
    }

    public List<QuizEp02> findAll() {
        return selectFrom(quizEp02)
                .fetch();
    }
}
