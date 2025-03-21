package com.ablez.admin.quiz.repository.episode3;

import static com.ablez.admin.first_db.quiz.entity.episode3.QQuiz1Ep03.quiz1Ep03;
import static com.ablez.admin.first_db.user.entity.QUserEp03.userEp03;

import com.ablez.admin.quiz.entity.episode3.Quiz1Ep03;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep3Quiz1Repository extends FirstQuerydsl4RepositorySupport {
    public Ep3Quiz1Repository() {
        super(Quiz1Ep03.class);
    }

    public List<Quiz1Ep03> findAll() {
        return selectFrom(quiz1Ep03)
                .innerJoin(quiz1Ep03.userId, userEp03).fetchJoin()
                .fetch();
    }
}
