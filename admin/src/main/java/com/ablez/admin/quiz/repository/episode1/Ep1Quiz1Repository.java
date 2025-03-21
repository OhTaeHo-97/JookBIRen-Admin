package com.ablez.admin.quiz.repository.episode1;

import static com.ablez.admin.quiz.entity.episode1.QQuiz1Ep01.quiz1Ep01;
import static com.ablez.admin.user.entity.QUserEp01.userEp01;

import com.ablez.admin.quiz.entity.episode1.Quiz1Ep01;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep1Quiz1Repository extends FirstQuerydsl4RepositorySupport {
    public Ep1Quiz1Repository() {
        super(Quiz1Ep01.class);
    }

    public List<Quiz1Ep01> findAll() {
        return selectFrom(quiz1Ep01)
                .innerJoin(quiz1Ep01.userId, userEp01).fetchJoin()
                .fetch();
    }
}
