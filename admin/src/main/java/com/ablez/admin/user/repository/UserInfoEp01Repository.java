package com.ablez.admin.user.repository;

import static com.ablez.admin.user.entity.QUserInfoEp01.userInfoEp01;

import com.ablez.admin.repository.Querydsl4RepositorySupport;
import com.ablez.admin.user.entity.UserInfoEp01;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoEp01Repository extends Querydsl4RepositorySupport {
    public UserInfoEp01Repository() {
        super(UserInfoEp01.class);
    }

    public Optional<UserInfoEp01> findByCode(String code) {
        UserInfoEp01 userInfo = selectFrom(userInfoEp01)
                .where(userInfoEp01.code.eq(code))
                .fetchOne();

        return Optional.ofNullable(userInfo);
    }
}
