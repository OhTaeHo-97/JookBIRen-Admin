package com.ablez.admin.second_db.user.repository;

import static com.ablez.admin.second_db.user.entity.QUserEp02.userEp02;

import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import com.ablez.admin.second_db.user.entity.UserEp02;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp02QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public UserEp02QuerydslRepository() {
        super(UserEp02.class);
    }

    public List<UserEp02> findAll() {
        return selectFrom(userEp02)
//                .innerJoin(userEp02.quiz0s, quiz0Ep02).fetchJoin()
//                .innerJoin(userEp02.quiz1s, quiz1Ep02).fetchJoin()
//                .innerJoin(userEp02.quiz2s, quiz2Ep02).fetchJoin()
//                .innerJoin(userEp02.quiz3, quiz3Ep02).fetchJoin()
//                .innerJoin(userEp02.wrongAnswers, wrongAnswerEp02).fetchJoin()
                .distinct()
                .fetch();
    }
}
