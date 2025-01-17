package com.ablez.admin.user.repository;

import static com.ablez.admin.user.entity.QUserEp00.userEp00;

import com.ablez.admin.repository.Querydsl4RepositorySupport;
import com.ablez.admin.user.entity.UserEp00;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp00Repository extends Querydsl4RepositorySupport {
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
}
