package com.ablez.admin.first_db.quiz.repository.episode2;

import static com.ablez.admin.first_db.quiz.entity.episode2.QQuiz3Ep02.quiz3Ep02;
import static com.ablez.admin.first_db.user.entity.QUserEp02.userEp02;

import com.ablez.admin.first_db.quiz.entity.episode2.Quiz3Ep02;
import com.ablez.admin.first_db.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep2Quiz3Repository extends FirstQuerydsl4RepositorySupport {
    public Ep2Quiz3Repository() {
        super(Quiz3Ep02.class);
    }

    public List<Quiz3Ep02> findAll() {
        return selectFrom(quiz3Ep02)
                .innerJoin(quiz3Ep02.userId, userEp02).fetchJoin()
                .fetch();
    }
}
