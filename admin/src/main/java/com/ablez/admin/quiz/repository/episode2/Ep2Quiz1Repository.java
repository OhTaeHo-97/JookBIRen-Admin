package com.ablez.admin.quiz.repository.episode2;

import static com.ablez.admin.quiz.entity.episode2.QQuiz1Ep02.quiz1Ep02;
import static com.ablez.admin.user.entity.QUserEp02.userEp02;

import com.ablez.admin.quiz.entity.episode2.Quiz1Ep02;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep2Quiz1Repository extends FirstQuerydsl4RepositorySupport {
    public Ep2Quiz1Repository() {
        super(Quiz1Ep02.class);
    }

    public List<Quiz1Ep02> findAll() {
        return selectFrom(quiz1Ep02)
                .innerJoin(quiz1Ep02.userId, userEp02).fetchJoin()
                .fetch();
    }
}
