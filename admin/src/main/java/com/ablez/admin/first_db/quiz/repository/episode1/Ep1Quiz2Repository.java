package com.ablez.admin.first_db.quiz.repository.episode1;

import static com.ablez.admin.first_db.quiz.entity.episode1.QQuiz2Ep01.quiz2Ep01;
import static com.ablez.admin.first_db.user.entity.QUserEp01.userEp01;

import com.ablez.admin.first_db.quiz.entity.episode1.Quiz2Ep01;
import com.ablez.admin.first_db.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep1Quiz2Repository extends FirstQuerydsl4RepositorySupport {
    public Ep1Quiz2Repository() {
        super(Quiz2Ep01.class);
    }

    public List<Quiz2Ep01> findAll() {
        return selectFrom(quiz2Ep01)
                .innerJoin(quiz2Ep01.userId, userEp01).fetchJoin()
                .fetch();
    }
}
