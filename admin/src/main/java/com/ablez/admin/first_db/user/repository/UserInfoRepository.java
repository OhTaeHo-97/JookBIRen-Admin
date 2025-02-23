package com.ablez.admin.first_db.user.repository;

import com.ablez.admin.first_db.repository.FirstQuerydsl4RepositorySupport;
import com.ablez.admin.first_db.user.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoRepository extends FirstQuerydsl4RepositorySupport {
    public UserInfoRepository() {
        super(UserInfo.class);
    }
}
