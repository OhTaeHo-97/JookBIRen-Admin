package com.ablez.admin.quiz.repository.episode2;

import static com.ablez.admin.first_db.quiz.entity.episode2.QQuiz2Ep02.quiz2Ep02;
import static com.ablez.admin.first_db.user.entity.QUserEp02.userEp02;

import com.ablez.admin.quiz.entity.episode2.Quiz2Ep02;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep2Quiz2Repository extends FirstQuerydsl4RepositorySupport {
    public Ep2Quiz2Repository() {
        super(Quiz2Ep02.class);
    }

    public List<Quiz2Ep02> findAll() {
        return selectFrom(quiz2Ep02)
                .innerJoin(quiz2Ep02.userId, userEp02).fetchJoin()
                .fetch();
    }
}
