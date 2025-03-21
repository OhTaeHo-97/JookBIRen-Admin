package com.ablez.admin.quiz.repository.episode1;

import static com.ablez.admin.first_db.quiz.entity.episode1.QQuiz0Ep01.quiz0Ep01;
import static com.ablez.admin.first_db.user.entity.QUserEp01.userEp01;

import com.ablez.admin.quiz.entity.episode1.Quiz0Ep01;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep1Quiz0Repository extends FirstQuerydsl4RepositorySupport {
    public Ep1Quiz0Repository() {
        super(Quiz0Ep01.class);
    }

    public List<Quiz0Ep01> findAll() {
        return selectFrom(quiz0Ep01)
                .innerJoin(quiz0Ep01.userId, userEp01).fetchJoin()
                .fetch();
    }
}
