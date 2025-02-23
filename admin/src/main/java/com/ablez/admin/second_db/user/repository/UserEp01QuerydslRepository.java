package com.ablez.admin.second_db.user.repository;

import static com.ablez.admin.second_db.user.entity.QUserEp01.userEp01;

import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import com.ablez.admin.second_db.user.entity.UserEp01;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp01QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public UserEp01QuerydslRepository() {
        super(UserEp01.class);
    }

    public List<UserEp01> findAll() {
        return selectFrom(userEp01)
//                .innerJoin(userEp01.quiz0s, quiz0Ep01).fetchJoin()
//                .innerJoin(userEp01.quiz1s, quiz1Ep01).fetchJoin()
//                .innerJoin(userEp01.quiz2s, quiz2Ep01).fetchJoin()
//                .innerJoin(userEp01.quiz3s, quiz3Ep01).fetchJoin()
//                .innerJoin(userEp01.quiz4s, quiz4Ep01).fetchJoin()
//                .innerJoin(userEp01.wrongAnswers, wrongAnswerEp01).fetchJoin()
                .distinct()
                .fetch();
    }
}
