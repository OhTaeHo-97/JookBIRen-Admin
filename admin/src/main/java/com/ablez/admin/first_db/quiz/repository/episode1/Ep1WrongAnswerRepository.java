package com.ablez.admin.first_db.quiz.repository.episode1;

import static com.ablez.admin.first_db.quiz.entity.episode1.QQuizEp01.quizEp01;
import static com.ablez.admin.first_db.quiz.entity.episode1.QWrongAnswerEp01.wrongAnswerEp01;
import static com.ablez.admin.first_db.user.entity.QUserEp01.userEp01;

import com.ablez.admin.first_db.quiz.entity.episode1.WrongAnswerEp01;
import com.ablez.admin.first_db.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep1WrongAnswerRepository extends FirstQuerydsl4RepositorySupport {
    public Ep1WrongAnswerRepository() {
        super(WrongAnswerEp01.class);
    }

    public List<WrongAnswerEp01> findAll() {
        return selectFrom(wrongAnswerEp01)
                .innerJoin(wrongAnswerEp01.user, userEp01).fetchJoin()
                .innerJoin(wrongAnswerEp01.quiz, quizEp01).fetchJoin()
                .fetch();
    }
}
