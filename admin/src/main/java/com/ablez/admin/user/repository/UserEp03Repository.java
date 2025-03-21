package com.ablez.admin.user.repository;

import static com.ablez.admin.user.entity.QUserEp03.userEp03;

import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import com.ablez.admin.user.entity.UserEp03;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp03Repository extends FirstQuerydsl4RepositorySupport {
    public UserEp03Repository() {
        super(UserEp03.class);
    }

    public Optional<UserEp03> findByCode(String code) {
        UserEp03 result = selectFrom(userEp03)
                .where(userEp03.code.eq(code))
                .distinct()
                .fetchOne();

        return Optional.ofNullable(result);
    }

    public List<UserEp03> findAll() {
        return selectFrom(userEp03)
                .fetch();
    }
}
