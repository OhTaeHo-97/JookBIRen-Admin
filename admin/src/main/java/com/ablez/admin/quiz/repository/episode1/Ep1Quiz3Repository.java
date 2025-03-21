package com.ablez.admin.quiz.repository.episode1;

import static com.ablez.admin.quiz.entity.episode1.QQuiz3Ep01.quiz3Ep01;
import static com.ablez.admin.user.entity.QUserEp01.userEp01;

import com.ablez.admin.quiz.entity.episode1.Quiz3Ep01;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Ep1Quiz3Repository extends FirstQuerydsl4RepositorySupport {
    public Ep1Quiz3Repository() {
        super(Quiz3Ep01.class);
    }

    public List<Quiz3Ep01> findAll() {
        return selectFrom(quiz3Ep01)
                .innerJoin(quiz3Ep01.userId, userEp01).fetchJoin()
                .fetch();
    }
}
