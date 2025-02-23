package com.ablez.admin.second_db.user.service;

import com.ablez.admin.second_db.user.entity.UserEp00;
import com.ablez.admin.second_db.user.entity.UserEp01;
import com.ablez.admin.second_db.user.entity.UserEp02;
import com.ablez.admin.second_db.user.repository.UserEp00QuerydslRepository;
import com.ablez.admin.second_db.user.repository.UserEp01QuerydslRepository;
import com.ablez.admin.second_db.user.repository.UserEp02QuerydslRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SecondUserService {
    private final UserEp00QuerydslRepository userEp0Repository;
    private final UserEp01QuerydslRepository userEp1Repository;
    private final UserEp02QuerydslRepository userEp2Repository;

    public List<UserEp00> findAllEp00() {
        return userEp0Repository.findAll();
    }

    public List<UserEp01> findAllEp01() {
        return userEp1Repository.findAll();
    }

    public List<UserEp02> findAllEp02() {
        return userEp2Repository.findAll();
    }
}
