package com.ablez.admin.quiz.repository.episode3;

import static com.ablez.admin.quiz.entity.episode3.QQuizEp03.quizEp03;
import static com.ablez.admin.quiz.entity.episode3.QWrongAnswerEp03.wrongAnswerEp03;
import static com.ablez.admin.user.entity.QUserEp03.userEp03;

import com.ablez.admin.quiz.entity.episode3.WrongAnswerEp03;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep3WrongAnswerRepository extends FirstQuerydsl4RepositorySupport {
    public Ep3WrongAnswerRepository() {
        super(WrongAnswerEp03.class);
    }

    public List<WrongAnswerEp03> findAll() {
        return selectFrom(wrongAnswerEp03)
                .innerJoin(wrongAnswerEp03.user, userEp03).fetchJoin()
                .innerJoin(wrongAnswerEp03.quiz, quizEp03).fetchJoin()
                .fetch();
    }
}
