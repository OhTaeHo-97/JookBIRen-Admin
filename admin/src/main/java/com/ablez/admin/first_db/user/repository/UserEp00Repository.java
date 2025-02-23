package com.ablez.admin.first_db.user.repository;

import static com.ablez.admin.first_db.user.entity.QUserEp00.userEp00;

import com.ablez.admin.first_db.repository.FirstQuerydsl4RepositorySupport;
import com.ablez.admin.first_db.user.entity.UserEp00;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp00Repository extends FirstQuerydsl4RepositorySupport {
    public UserEp00Repository() {
        super(UserEp00.class);
    }

    public Optional<UserEp00> findByCode(String code) {
        UserEp00 userInfo = selectFrom(userEp00)
                .where(userEp00.code.eq(code))
                .distinct()
                .fetchOne();
        return Optional.ofNullable(userInfo);
    }

    public List<UserEp00> findAll() {
        return selectFrom(userEp00)
                .fetch();
    }
}
