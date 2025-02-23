package com.ablez.admin.second_db.user.repository;

import static com.ablez.admin.second_db.user.entity.QUserEp00.userEp00;

import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import com.ablez.admin.second_db.user.entity.UserEp00;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp00QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public UserEp00QuerydslRepository() {
        super(UserEp00.class);
    }

    public List<UserEp00> findAll() {
        return selectFrom(userEp00)
                .fetch();
    }
}
