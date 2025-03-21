package com.ablez.admin.user.service;

import static com.ablez.admin.utils.AnswerConstants.EP01_SUSPECT;
import static com.ablez.admin.utils.AnswerConstants.EP02_SUSPECT1;
import static com.ablez.admin.utils.AnswerConstants.EP02_SUSPECT2;
import static com.ablez.admin.utils.AnswerConstants.EP03_SUSPECT;

import com.ablez.admin.security.entity.Authority;
import com.ablez.admin.user.entity.UserEp00;
import com.ablez.admin.user.entity.UserEp01;
import com.ablez.admin.user.entity.UserEp02;
import com.ablez.admin.user.entity.UserEp03;
import com.ablez.admin.user.entity.UserInfo;
import com.ablez.admin.user.repository.UserEp00Repository;
import com.ablez.admin.user.repository.UserEp01Repository;
import com.ablez.admin.user.repository.UserEp02Repository;
import com.ablez.admin.user.repository.UserEp03Repository;
import com.ablez.admin.user.repository.UserInfoJpaRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService {
    private static final int CODE_LENGTH = 6;

    private final UserEp00Repository userEp00Repository;
    private final UserEp01Repository userEp01Repository;
    private final UserEp02Repository userEp02Repository;
    private final UserEp03Repository userEp03Repository;
    private final UserInfoJpaRepository userInfoJpaRepository;

    public Workbook registerCodesInEp0(int count) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("코드 정보");
        for (int user = 0; user < count; user++) {
            Row row = sheet.createRow(user);
            String code = registerCodeInEp0();
            row.createCell(0).setCellValue(code);
        }
        return workbook;
    }

    private String registerCodeInEp0() {
        String code = generateCode(0);
        UserInfo userInfo = new UserInfo(code);
        userInfoJpaRepository.save(userInfo);
        Authority authority = new Authority("ROLE_USER", userInfo);
        userInfo.addRole(authority);
        UserEp00 userEp00 = new UserEp00(code, userInfo);
        return code;
    }

    private String generateCode(int episode) {
        String code = makeCode(episode);
        while (userEp00Repository.findByCode(code).isPresent()) {
            code = makeCode(episode);
        }
        return code;
    }

    private String makeCode(int episode) {
        String code = String.format("EP%d", episode) + RandomStringUtils.randomAlphanumeric(CODE_LENGTH).toUpperCase();
        return code;
    }

    public Workbook registerCodesInWeb(int episode, int count) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(String.format("에피소드%d 코드 정보", episode));
        for (int user = 0; user < count; user++) {
            Row row = sheet.createRow(user);
            String code = registerCodeInWeb(episode);
            row.createCell(0).setCellValue(code);
        }
        return workbook;
    }

    private String registerCodeInWeb(int episode) {
        String code = generateCodeInWeb(episode);
        if (episode == 1) {
            UserInfo userInfo = new UserInfo(code);
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);
            UserEp01 userEp01 = new UserEp01(code, userInfo);
        } else if (episode == 2) {
            UserInfo userInfo = new UserInfo(code);
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);
            UserEp02 userEp02 = new UserEp02(code, userInfo);
        } else if (episode == 3) {
            UserInfo userInfo = new UserInfo(code);
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);
            UserEp03 userEp03 = new UserEp03(code, userInfo);
        }
        return code;
    }

    private String generateCodeInWeb(int episode) {
        String code = makeCode(episode);
        if (episode == 1) {
            while (userEp01Repository.findByCode(code).isPresent()) {
                code = makeCode(episode);
            }
        } else if (episode == 2) {
            while (userEp02Repository.findByCode(code).isPresent()) {
                code = makeCode(episode);
            }
        } else if (episode == 3) {
            while (userEp03Repository.findByCode(code).isPresent()) {
                code = makeCode(episode);
            }
        }
        return code;
    }

    public Workbook makeUserInfoEp01(Workbook workbook) {
        // 이름, 코드, 전화번호, 플랫폼, 닉네임, 주문번호, 금액, 에피소드, 주소, 생성일
        List<UserEp01> users = userEp01Repository.findAll();

        Sheet sheet = workbook.createSheet("에피소드1 유저 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("에피소드");
        row.createCell(2).setCellValue("생성일");
        row.createCell(3).setCellValue("정답 시간");
        row.createCell(4).setCellValue("용의자");
        row.createCell(5).setCellValue("첫 로그인 시간");
        row.createCell(6).setCellValue("푼 문제 개수");
        row.createCell(7).setCellValue("탐정 점수");

        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            row = sheet.createRow(userIdx + 1);
            UserEp01 user = users.get(userIdx);

            row.createCell(0).setCellValue(user.getCode());
            row.createCell(1).setCellValue(1);
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue(parseLocalDateTime(user.getAnswerTime()));
            row.createCell(4).setCellValue(EP01_SUSPECT.get(user.getCriminal()));
            row.createCell(5).setCellValue(parseLocalDateTime(user.getFirstLoginTime()));
            row.createCell(6).setCellValue(user.getSolvedQuizCount());
            row.createCell(7).setCellValue(user.getScore());
        }

        return workbook;
    }

    public Workbook makeUserInfoEp02(Workbook workbook) {
        // 이름, 코드, 전화번호, 플랫폼, 닉네임, 주문번호, 금액, 에피소드, 주소, 생성일
        List<UserEp02> users = userEp02Repository.findAll();

        Sheet sheet = workbook.createSheet("에피소드2 유저 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("에피소드");
        row.createCell(2).setCellValue("생성일");
        row.createCell(3).setCellValue("정답 시간");
        row.createCell(4).setCellValue("용의자1");
        row.createCell(5).setCellValue("용의자2");
        row.createCell(6).setCellValue("첫 로그인 시간");
        row.createCell(7).setCellValue("푼 문제 개수");
        row.createCell(8).setCellValue("탐정 점수");

        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            row = sheet.createRow(userIdx + 1);
            UserEp02 user = users.get(userIdx);

            row.createCell(0).setCellValue(user.getCode());
            row.createCell(1).setCellValue(2);
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue(parseLocalDateTime(user.getAnswerTime()));
            row.createCell(4).setCellValue(EP02_SUSPECT1.get(user.getCriminal1()));
            row.createCell(5).setCellValue(EP02_SUSPECT2.get(user.getCriminal2()));
            row.createCell(6).setCellValue(parseLocalDateTime(user.getFirstLoginTime()));
            row.createCell(7).setCellValue(user.getSolvedQuizCount());
            row.createCell(8).setCellValue(user.getScore());
        }

        return workbook;
    }

    public Workbook makeUserInfoEp03(Workbook workbook) {
        // 이름, 코드, 전화번호, 플랫폼, 닉네임, 주문번호, 금액, 에피소드, 주소, 생성일
        List<UserEp03> users = userEp03Repository.findAll();

        Sheet sheet = workbook.createSheet("에피소드3 유저 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("에피소드");
        row.createCell(2).setCellValue("생성일");
        row.createCell(3).setCellValue("정답 시간");
        row.createCell(4).setCellValue("용의자");
        row.createCell(5).setCellValue("첫 로그인 시간");
        row.createCell(6).setCellValue("푼 문제 개수");
        row.createCell(7).setCellValue("탐정 점수");

        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            row = sheet.createRow(userIdx + 1);
            UserEp03 user = users.get(userIdx);

            row.createCell(0).setCellValue(user.getCode());
            row.createCell(1).setCellValue(3);
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue(parseLocalDateTime(user.getAnswerTime()));
            row.createCell(4).setCellValue(EP03_SUSPECT.get(user.getCriminal()));
            row.createCell(5).setCellValue(parseLocalDateTime(user.getFirstLoginTime()));
            row.createCell(6).setCellValue(user.getSolvedQuizCount());
            row.createCell(7).setCellValue(user.getScore());
        }

        return workbook;
    }

    private String parseLocalDateTime(LocalDateTime time) {
        if (time == null) {
            return "";
        }
        return time.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    // 점수 변경
    public void updateScore() {
        List<UserEp01> usersEp1 = userEp01Repository.findAll();
        for (UserEp01 user : usersEp1) {
            int score = calculateScoreEp1(user);
            user.setScore(score);
        }

        List<UserEp02> usersEp2 = userEp02Repository.findAll();
        for (UserEp02 user : usersEp2) {
            int score = calculateScoreEp2(user);
            user.setScore(score);
        }

        List<UserEp03> usersEp3 = userEp03Repository.findAll();
        for (UserEp03 user : usersEp3) {
            int score = calculateScoreEp3(user);
            user.setScore(score);
        }
    }

    private int calculateScoreEp1(UserEp01 user) {
        if (user.getAnswerTime() == null) {
            return 0;
        }

        int score = 0;
        if (user.getCriminal() == 2) {
            score += 5;
        } else {
            score += 1;
        }

        if (user.getAnswerCount() == 0) {
            score += 5;
        } else if (user.getAnswerCount() <= 3) {
            score += 4;
        } else if (user.getAnswerCount() <= 5) {
            score += 3;
        } else {
            score += 1;
        }

        if (user.getSolvedQuizCount() >= 20) {
            score += 5;
        } else if (user.getSolvedQuizCount() >= 17) {
            score += 3;
        } else if (user.getSolvedQuizCount() >= 14) {
            score += 2;
        } else {
            score += 1;
        }

        return score;
    }

    private int calculateScoreEp2(UserEp02 user) {
        if (user.getAnswerTime() == null) {
            return 0;
        }

        int score = 0;
        if (user.getCriminal1() == 3) {
            score += 5;
        } else {
            score += 1;
        }

        if (user.getAnswerCount() == 1) {
            score += 5;
        } else if (user.getAnswerCount() <= 3) {
            score += 4;
        } else if (user.getAnswerCount() <= 5) {
            score += 3;
        } else {
            score += 1;
        }

        if (user.getSolvedQuizCount() >= 16) {
            score += 5;
        } else if (user.getSolvedQuizCount() >= 13) {
            score += 3;
        } else if (user.getSolvedQuizCount() >= 10) {
            score += 2;
        } else {
            score += 1;
        }

        return score;
    }

    private int calculateScoreEp3(UserEp03 user) {
        if (user.getAnswerTime() == null) {
            return 0;
        }
        
        int score = 0;
        if (user.getCriminal() == 1) {
            score += 5;
        } else {
            score += 1;
        }

        if (user.getAnswerCount() <= 1) {
            score += 5;
        } else if (user.getAnswerCount() <= 3) {
            score += 4;
        } else if (user.getAnswerCount() <= 5) {
            score += 3;
        } else {
            score += 1;
        }

        if (user.getSolvedQuizCount() >= 17) {
            score += 5;
        } else if (user.getSolvedQuizCount() >= 14) {
            score += 3;
        } else if (user.getSolvedQuizCount() >= 11) {
            score += 2;
        } else {
            score += 1;
        }

        return score;
    }
}
