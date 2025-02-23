package com.ablez.admin.second_db.user.repository;

import static com.ablez.admin.second_db.user.entity.QUserInfoEp01.userInfoEp01;

import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import com.ablez.admin.second_db.user.entity.UserInfoEp01;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoEp01Repository extends SecondQuerydsl4RepositorySupport {
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
