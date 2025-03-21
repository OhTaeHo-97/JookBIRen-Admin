package com.ablez.admin.user.repository;

import static com.ablez.admin.first_db.user.entity.QUserEp02.userEp02;

import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import com.ablez.admin.user.entity.UserEp02;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserEp02Repository extends FirstQuerydsl4RepositorySupport {
    public UserEp02Repository() {
        super(UserEp02.class);
    }

    public Optional<UserEp02> findByCode(String code) {
        UserEp02 result = selectFrom(userEp02)
                .where(userEp02.code.eq(code))
                .distinct()
                .fetchOne();

        return Optional.ofNullable(result);
    }

    public List<UserEp02> findAll() {
        return selectFrom(userEp02)
                .fetch();
    }
}
