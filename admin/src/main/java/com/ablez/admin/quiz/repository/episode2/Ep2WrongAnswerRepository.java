package com.ablez.admin.quiz.repository.episode2;

import static com.ablez.admin.quiz.entity.episode2.QQuizEp02.quizEp02;
import static com.ablez.admin.quiz.entity.episode2.QWrongAnswerEp02.wrongAnswerEp02;
import static com.ablez.admin.user.entity.QUserEp02.userEp02;

import com.ablez.admin.quiz.entity.episode2.WrongAnswerEp02;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep2WrongAnswerRepository extends FirstQuerydsl4RepositorySupport {
    public Ep2WrongAnswerRepository() {
        super(WrongAnswerEp02.class);
    }

    public List<WrongAnswerEp02> findAll() {
        return selectFrom(wrongAnswerEp02)
                .innerJoin(wrongAnswerEp02.user, userEp02).fetchJoin()
                .innerJoin(wrongAnswerEp02.quiz, quizEp02).fetchJoin()
                .fetch();
    }
}
