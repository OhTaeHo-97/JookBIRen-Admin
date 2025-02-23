package com.ablez.admin.second_db.user.repository;

import static com.ablez.admin.second_db.user.entity.QUserInfoEp02.userInfoEp02;

import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import com.ablez.admin.second_db.user.entity.UserInfoEp02;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoEp02Repository extends SecondQuerydsl4RepositorySupport {
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
