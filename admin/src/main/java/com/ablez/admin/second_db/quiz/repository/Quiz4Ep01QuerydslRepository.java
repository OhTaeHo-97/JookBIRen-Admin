package com.ablez.admin.second_db.quiz.repository;

import static com.ablez.admin.second_db.quiz.entity.QQuiz4Ep01.quiz4Ep01;
import static com.ablez.admin.second_db.user.entity.QUserEp01.userEp01;

import com.ablez.admin.second_db.quiz.entity.Quiz4Ep01;
import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import com.ablez.admin.second_db.user.entity.UserEp01;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class Quiz4Ep01QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public Quiz4Ep01QuerydslRepository() {
        super(Quiz4Ep01.class);
    }

    public Optional<Quiz4Ep01> findByUser(UserEp01 user) {
        return Optional.ofNullable(
                selectFrom(quiz4Ep01)
                        .innerJoin(quiz4Ep01.userId, userEp01).fetchJoin()
                        .where(quiz4Ep01.userId.eq(user))
                        .distinct()
                        .fetchOne()
        );
    }
}
