package com.ablez.admin.user.repository;

import static com.ablez.admin.user.entity.QUserInfoEp02.userInfoEp02;

import com.ablez.admin.repository.Querydsl4RepositorySupport;
import com.ablez.admin.user.entity.UserInfoEp02;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoEp02Repository extends Querydsl4RepositorySupport {
    public UserInfoEp02Repository() {
        super(UserInfoEp02.class);
    }

    public Optional<UserInfoEp02> findByCode(String code) {
        UserInfoEp02 userInfo = selectFrom(userInfoEp02)
                .where(userInfoEp02.code.eq(code))
                .fetchOne();

        return Optional.ofNullable(userInfo);
    }
}
