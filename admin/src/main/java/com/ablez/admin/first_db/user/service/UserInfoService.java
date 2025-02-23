package com.ablez.admin.first_db.user.service;

import com.ablez.admin.second_db.user.entity.UserInfoEp01;
import com.ablez.admin.second_db.user.entity.UserInfoEp02;
import com.ablez.admin.second_db.user.repository.UserInfoEp01JpaRepository;
import com.ablez.admin.second_db.user.repository.UserInfoEp01Repository;
import com.ablez.admin.second_db.user.repository.UserInfoEp02JpaRepository;
import com.ablez.admin.second_db.user.repository.UserInfoEp02Repository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserInfoService {
    private static final int CODE_LENGTH = 6;

    private final UserInfoEp01Repository userInfoEp01Repository;
    private final UserInfoEp01JpaRepository userInfoEp01JpaRepository;
    private final UserInfoEp02Repository userInfoEp02Repository;
    private final UserInfoEp02JpaRepository userInfoEp02JpaRepository;

    public UserInfoEp01 makeUserInfoEp01(String phone) {
        String code = generateCode(1);
        UserInfoEp01 userInfo = new UserInfoEp01(code, phone);
        userInfoEp01JpaRepository.save(userInfo);
        return userInfo;
    }

    public UserInfoEp02 makeUserInfoEp02(String phone) {
        String code = generateCode(2);
        UserInfoEp02 userInfo = new UserInfoEp02(code, phone);
        userInfoEp02JpaRepository.save(userInfo);
        return userInfo;
    }

    private String generateCode(int episode) {
        String code = "EP" + episode + RandomStringUtils.randomAlphanumeric(CODE_LENGTH);

        if (episode == 1) {
            while (userInfoEp01Repository.findByCode(code).isPresent()) {
                code = "EP" + episode + RandomStringUtils.randomAlphanumeric(CODE_LENGTH);
            }
        } else if (episode == 2) {
            while (userInfoEp02Repository.findByCode(code).isPresent()) {
                code = "EP" + episode + RandomStringUtils.randomAlphanumeric(CODE_LENGTH);
            }
        }

        return code;
    }
}
