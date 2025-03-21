package com.ablez.admin.quiz.repository.episode1;

import static com.ablez.admin.quiz.entity.episode1.QQuiz4Ep01.quiz4Ep01;
import static com.ablez.admin.user.entity.QUserEp01.userEp01;

import com.ablez.admin.quiz.entity.episode1.Quiz4Ep01;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep1Quiz4Repository extends FirstQuerydsl4RepositorySupport {
    public Ep1Quiz4Repository() {
        super(Quiz4Ep01.class);
    }

    public List<Quiz4Ep01> findAll() {
        return selectFrom(quiz4Ep01)
                .innerJoin(quiz4Ep01.userId, userEp01).fetchJoin()
                .fetch();
    }
}
