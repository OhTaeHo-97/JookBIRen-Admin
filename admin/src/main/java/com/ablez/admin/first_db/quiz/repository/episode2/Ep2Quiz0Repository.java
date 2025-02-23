package com.ablez.admin.first_db.quiz.repository.episode2;

import static com.ablez.admin.first_db.quiz.entity.episode2.QQuiz0Ep02.quiz0Ep02;
import static com.ablez.admin.first_db.user.entity.QUserEp02.userEp02;

import com.ablez.admin.first_db.quiz.entity.episode2.Quiz0Ep02;
import com.ablez.admin.first_db.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep2Quiz0Repository extends FirstQuerydsl4RepositorySupport {
    public Ep2Quiz0Repository() {
        super(Quiz0Ep02.class);
    }

    public List<Quiz0Ep02> findAll() {
        return selectFrom(quiz0Ep02)
                .innerJoin(quiz0Ep02.userId, userEp02).fetchJoin()
                .fetch();
    }
}
