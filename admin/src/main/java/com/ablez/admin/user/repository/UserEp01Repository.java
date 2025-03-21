package com.ablez.admin.user.repository;

import static com.ablez.admin.user.entity.QUserEp01.userEp01;

import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import com.ablez.admin.user.entity.UserEp01;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp01Repository extends FirstQuerydsl4RepositorySupport {
    public UserEp01Repository() {
        super(UserEp01.class);
    }

    public Optional<UserEp01> findByCode(String code) {
        UserEp01 result = selectFrom(userEp01)
                .where(userEp01.code.eq(code))
                .distinct()
                .fetchOne();

        return Optional.ofNullable(result);
    }

    public List<UserEp01> findAll() {
        return selectFrom(userEp01)
                .fetch();
    }
}
