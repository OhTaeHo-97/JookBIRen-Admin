package com.ablez.admin.first_db.user.repository;

import com.ablez.admin.first_db.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
}
