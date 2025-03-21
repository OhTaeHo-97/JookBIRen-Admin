package com.ablez.admin.quiz.repository.episode3;

import static com.ablez.admin.quiz.entity.episode3.QQuiz3Ep03.quiz3Ep03;
import static com.ablez.admin.user.entity.QUserEp03.userEp03;

import com.ablez.admin.quiz.entity.episode3.Quiz3Ep03;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep3Quiz3Repository extends FirstQuerydsl4RepositorySupport {
    public Ep3Quiz3Repository() {
        super(Quiz3Ep03.class);
    }

    public List<Quiz3Ep03> findAll() {
        return selectFrom(quiz3Ep03)
                .innerJoin(quiz3Ep03.userId, userEp03).fetchJoin()
                .fetch();
    }
}
